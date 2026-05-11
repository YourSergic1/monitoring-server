package github.titandea.controller;

import github.titandea.dto.response.SystemMetricsResponse;
import github.titandea.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
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

    @GetMapping("/{id}/metrics/range")
    public List<SystemMetricsResponse> getAgentMetrics(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "30m") String range) {
        return metricsService.getMetricsForAgent(id, range);
    }

    @GetMapping("/{id}/metrics/dates")
    public List<SystemMetricsResponse> getAgentMetrics(
            @PathVariable UUID id,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        return metricsService.getMetricsForAgent(id, startTime,endTime);
    }
}
