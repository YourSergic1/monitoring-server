package github.titandea.service;

import github.titandea.dto.response.AgentSummaryResponse;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с агентами.
 */
@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;

    private final EntityToResponseDtoMapper entityToResponseDtoMapper;

    /**
     * Получение списка агентов.
     */
    public List<AgentSummaryResponse> getAllAgentsSummaryByOrganization(UUID organizationId) {
        return agentRepository.findByOrganizationId(organizationId).stream()
                .map(agentEntity ->
                        entityToResponseDtoMapper.toAgentSummaryResponse(agentEntity))
                .collect(Collectors.toList());
    }
}
