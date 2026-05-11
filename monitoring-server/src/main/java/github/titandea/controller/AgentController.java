package github.titandea.controller;

import github.titandea.dto.response.SystemMetricsResponse;
import github.titandea.service.AgentService;
import github.titandea.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с агентами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final MetricsService metricsService;

    @GetMapping("/{id}/metrics")
    public List<SystemMetricsResponse> getAgentMetrics(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "30m") String range) {

        Duration duration = parseRange(range);
        return metricsService.getMetricsForAgent(id, range);
    }

    private Duration parseRange(String range) {
        return switch (range) {
            case "2h" -> Duration.ofHours(2);
            case "4h" -> Duration.ofHours(4);
            default -> Duration.ofMinutes(30);
        };
    }
}
