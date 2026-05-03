package github.titandea.service;

import github.titandea.dto.InitInfo;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.OrganizationEntity;
import github.titandea.repository.AgentRepository;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgentInitializationService {

    private final AgentRepository agentRepository;

    private final OrganizationRepository organizationRepository;

    public void initAgent(InitInfo initInfo) {
        Optional<AgentEntity> agentEntityCheck = agentRepository.findByLocalIp(initInfo.getLocalIp());
        if (agentEntityCheck.isPresent()) {
            log.info("Агент уже проинициализирован.");
            return;
        }
        Optional<OrganizationEntity> organizationEntity = organizationRepository.findById(UUID.fromString(initInfo.getOrganization()));
        if (organizationEntity.isPresent()) {
            AgentEntity agentEntity = new AgentEntity();
            agentEntity.setOrganization(organizationEntity.get());
            if (initInfo.getLocalIp() != null) agentEntity.setLocalIp(initInfo.getLocalIp());
            if (initInfo.getContinuous() != null) agentEntity.setContinuous(initInfo.getContinuous());
            if (initInfo.getContinuous() != null && !initInfo.getContinuous()) {
                if (initInfo.getStartTime() != null) agentEntity.setStartTime(initInfo.getStartTime());
                if (initInfo.getEndTime() != null) agentEntity.setEndTime(initInfo.getEndTime());
            }
            agentRepository.save(agentEntity);
        } else {
            throw new RuntimeException("UUID организации не найден.");
        }
    }
}

