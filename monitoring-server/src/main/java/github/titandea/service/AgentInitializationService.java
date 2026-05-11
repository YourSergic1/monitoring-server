package github.titandea.service;

import github.titandea.dto.create.Agent;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.OrganizationEntity;
import github.titandea.enums.AgentState;
import github.titandea.repository.AgentRepository;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для инициализации агента.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AgentInitializationService {

    private final AgentRepository agentRepository;

    private final OrganizationRepository organizationRepository;

    public void initAgent(Agent agent) {
        Optional<AgentEntity> agentEntityCheck = agentRepository.findByLocalIp(agent.getLocalIp());
        if (agentEntityCheck.isPresent()) {
            log.info("Агент уже проинициализирован.");
            return;
        }
        Optional<OrganizationEntity> organizationEntity = organizationRepository.findById(UUID.fromString(agent.getOrganization()));
        if (organizationEntity.isPresent()) {
            AgentEntity agentEntity = new AgentEntity();
            agentEntity.setOrganization(organizationEntity.get());
            if (agent.getLocalIp() != null) agentEntity.setLocalIp(agent.getLocalIp());
            if (agent.getContinuous() != null) agentEntity.setContinuous(agent.getContinuous());
            if (agent.getContinuous() != null && !agent.getContinuous()) {
                if (agent.getStartTime() != null) agentEntity.setStartTime(agent.getStartTime());
                if (agent.getEndTime() != null) agentEntity.setEndTime(agent.getEndTime());
            }
            agentEntity.setState(AgentState.OK);
            agentRepository.save(agentEntity);
        } else {
            throw new RuntimeException("UUID организации не найден.");
        }
    }
}

