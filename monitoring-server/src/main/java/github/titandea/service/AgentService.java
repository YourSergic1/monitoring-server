package github.titandea.service;

import github.titandea.dto.response.AgentResponse;
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
     * Возвращает список агентов, принадлежащих указанной организации.
     */
    public List<AgentResponse> getAgentsByOrganization(UUID organizationUUID) {
        return agentRepository.findByOrganizationId(organizationUUID).stream()
                .map(agentEntity -> entityToResponseDtoMapper.toAgentResponse(agentEntity))
                .collect(Collectors.toList());
    }
}
