package github.titandea.service;

import github.titandea.dto.response.SystemMetricsResponse;
import github.titandea.repository.SystemMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetricsService {
    private final SystemMetricsRepository repository;

    public List<SystemMetricsResponse> getMetricsForAgent(UUID id, String range){

    }

    private Duration parseRange(String range) {
        return switch (range) {
            case "2h" -> Duration.ofHours(2);
            case "4h" -> Duration.ofHours(4);
            default -> Duration.ofMinutes(30);
        };
    }
}
