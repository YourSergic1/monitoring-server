package github.titandea.service;

import github.titandea.dto.create.SystemMetrics;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.SystemMetricsEntity;
import github.titandea.mapper.CreateDtoToEntityMapper;
import github.titandea.repository.AgentRepository;
import github.titandea.repository.SystemMetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Сервис считывания системных метрик из кафки.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsIngestionService {

    private final SystemMetricsRepository systemMetricsRepository;

    private final AgentRepository agentRepository;

    private final CreateDtoToEntityMapper createDtoToEntityMapper;

    @Transactional
    public void processAndSave(SystemMetrics dto) {
        if (systemMetricsRepository.existsByLocalIpAndDateTime(dto.getLocalIp(), dto.getDateTime())) {
            log.debug("Метрики для {} уже существуют, пропуск.", dto.getLocalIp());
            return;
        }
        Optional<AgentEntity> agentEntity = agentRepository.findByLocalIp(dto.getLocalIp());
        if (agentEntity.isEmpty()) {
            log.debug("Агент {} не проинициализирован.", dto.getLocalIp());
            return;
        }
        SystemMetricsEntity entity = createDtoToEntityMapper.toSystemMetricsEntity(dto, agentEntity.get());
        agentEntity.get().setLastMetricReceived(LocalDateTime.now());
        agentRepository.save(agentEntity.get());
        systemMetricsRepository.save(entity);
    }
}