package github.titandea.service;

import github.titandea.dto.response.SystemMetricsResponse;
import github.titandea.entity.SystemMetricsEntity;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.SystemMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetricsService {
    private final SystemMetricsRepository repository;

    private final EntityToResponseDtoMapper mapper;

    public List<SystemMetricsResponse> getMetricsForAgent(UUID agentId, String range) {
        Duration duration = parseRange(range);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minus(duration);

        List<SystemMetricsEntity> entities = repository.findByAgentIdAndDateTimeRange(agentId, startTime, now);

        return entities.stream()
                .map(entity -> mapper.toSystemMetricsResponse(entity))
                .toList();
    }

    private Duration parseRange(String range) {
        return switch (range) {
            case "2h" -> Duration.ofHours(2);
            case "4h" -> Duration.ofHours(4);
            default -> Duration.ofMinutes(30);
        };
    }

}