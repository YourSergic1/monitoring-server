package github.titandea.controller;

import github.titandea.dto.response.AgentSummaryResponse;
import github.titandea.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с агентами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

    /**
     * Получение списка организаций кратко.
     */
    @GetMapping
    public List<AgentSummaryResponse> getAllOrganizations(@RequestParam(required = true) UUID organizationId) {
        return agentService.getAllAgentsSummaryByOrganization(organizationId);
    }
}
