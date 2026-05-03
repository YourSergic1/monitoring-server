package github.titandea.service;

import github.titandea.dto.SystemMetrics;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.SystemMetricsEntity;
import github.titandea.mapper.MetricsMapper;
import github.titandea.repository.AgentRepository;
import github.titandea.repository.SystemMetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsIngestionService {

    private final SystemMetricsRepository systemMetricsRepository;

    private final AgentRepository agentRepository;

    private final MetricsMapper mapper;

    @Transactional
    public void processAndSave(SystemMetrics dto) {
        if (systemMetricsRepository.existsByLocalIpAndDateTime(dto.getLocalIp(), dto.getDateTime())) {
            log.debug("Метрики для {} уже существуют, пропуск.", dto.getLocalIp());
            return;
        }
        Optional<AgentEntity> agentEntity = agentRepository.findByLocalIp(dto.getLocalIp());
        if (!agentEntity.isPresent()) {
            log.debug("Агент {} не проинициализирован.", dto.getLocalIp());
            return;
        }
        SystemMetricsEntity entity = mapper.toEntity(dto, agentEntity.get());
        linkBidirectionalRelations(entity);
        agentEntity.get().setLastMetricReceived(LocalDateTime.now());
        agentRepository.save(agentEntity.get());
        systemMetricsRepository.save(entity);
    }

    private void linkBidirectionalRelations(SystemMetricsEntity entity) {
        if (entity.getCpuMetricsEntities() != null) {
            entity.getCpuMetricsEntities().forEach(cpu -> cpu.setSystem(entity));
        }
        if (entity.getMemoryMetricsEntities() != null) {
            entity.getMemoryMetricsEntities().forEach(mem -> mem.setSystem(entity));
        }
        if (entity.getDiskMetricsEntities() != null) {
            entity.getDiskMetricsEntities().forEach(disk -> disk.setSystem(entity));
        }
        if (entity.getNetworkMetricsEntities() != null) {
            entity.getNetworkMetricsEntities().forEach(net -> net.setSystem(entity));
        }
    }
}