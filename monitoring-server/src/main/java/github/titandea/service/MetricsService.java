package github.titandea.service;

import github.titandea.dto.response.SystemMetricsResponse;
import github.titandea.entity.SystemMetricsEntity;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.SystemMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с метриками.
 */
@Service
@RequiredArgsConstructor
public class MetricsService {
    private final SystemMetricsRepository repository;

    private final EntityToResponseDtoMapper mapper;

    public List<SystemMetricsResponse> getMetricsForAgent(UUID agentId, String range) {
        Duration duration = parseRange(range);
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(duration);

        List<SystemMetricsEntity> entities = repository.findByAgentIdAndDateTimeRange(agentId, startTime, endTime);

        return entities.stream()
                .map(entity -> mapper.toSystemMetricsResponse(entity))
                .toList();
    }

    public List<SystemMetricsResponse> getMetricsForAgent(UUID agentId, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return Collections.emptyList();
        }
        List<SystemMetricsEntity> entities = repository.findByAgentIdAndDateTimeRange(agentId, startTime, endTime);

        return entities.stream()
                .map(entity -> mapper.toSystemMetricsResponse(entity))
                .toList();
    }

    private Duration parseRange(String range) {
        return switch (range) {
            case "1h" -> Duration.ofHours(1);
            case "2h" -> Duration.ofHours(2);
            case "4h" -> Duration.ofHours(4);
            case "24h" -> Duration.ofHours(24);
            default -> Duration.ofMinutes(30);
        };
    }

}