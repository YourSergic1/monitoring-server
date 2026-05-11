package github.titandea.controller;

import github.titandea.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с агентами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

}
