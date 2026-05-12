package github.titandea.service;

import github.titandea.dto.warning.AgentWarning;
import github.titandea.dto.warning.OrganizationWarning;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.OrganizationEntity;
import github.titandea.enums.AgentState;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static github.titandea.enums.AgentState.*;

/**
 * Сервис для выставления состояния в зависимости от состояния агента.
 */
@Service
@RequiredArgsConstructor
public class AgentStateService {

    private final OrganizationRepository organizationRepository;

    private final EmailService emailService;

    @Transactional
    @Scheduled(fixedRate = 300_000)
    public void changeAgentState() {
        List<OrganizationWarning> warnings = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        List<OrganizationEntity> organizations = organizationRepository.findAll();

        for (OrganizationEntity organization : organizations) {
            AgentState worstOrgState = OK;
            List<AgentEntity> agents = organization.getAgents();
            OrganizationWarning organizationWarning = new OrganizationWarning();
            for (AgentEntity agent : agents) {
                AgentState agentState;

                boolean outsideWorkingHours = !agent.getContinuous() &&
                        (currentTime.isBefore(agent.getStartTime()) || currentTime.isAfter(agent.getEndTime()));
                boolean noMetrics = agent.getLastMetricReceived() == null;

                if (outsideWorkingHours || noMetrics) {
                    agentState = OFFLINE;
                } else {
                    long minutesAgo = Duration.between(agent.getLastMetricReceived(), now).toMinutes();
                    if (minutesAgo > 30) {
                        agentState = CRITICAL;
                    } else if (minutesAgo > 10) {
                        agentState = WARNING;
                    } else {
                        agentState = OK;
                    }
                }
                agent.setState(agentState);
                worstOrgState = getWorstState(worstOrgState, agentState);
                createAgentWarning(organizationWarning, agent);
            }
            organization.setState(worstOrgState);
            initOrganizationWarning(warnings, organizationWarning, organization);
        }
        emailService.sendWarnings(warnings);
    }

    private void createAgentWarning(OrganizationWarning organizationWarning,
                                    AgentEntity agentEntity) {
        if (agentEntity.getState().equals(CRITICAL) || agentEntity.getState().equals(WARNING)) {
            AgentWarning agentWarning = new AgentWarning();
            agentWarning.setId(agentEntity.getId());
            agentWarning.setLocalIp(agentEntity.getLocalIp());
            agentWarning.setLastMetricReceived(agentEntity.getLastMetricReceived());
            agentWarning.setState(agentEntity.getState());
            organizationWarning.getAgentWarningList().add(agentWarning);
        }
    }

    private void initOrganizationWarning(List<OrganizationWarning> organizationWarningList,
                                         OrganizationWarning organizationWarning,
                                         OrganizationEntity organizationEntity) {
        if (organizationEntity.getState().equals(CRITICAL) || organizationEntity.getState().equals(WARNING)) {
            organizationWarning.setName(organizationEntity.getName());
            organizationWarning.setId(organizationEntity.getId());
            organizationWarningList.add(organizationWarning);
        }
    }

    private AgentState getWorstState(AgentState currentOrgState, AgentState newAgentState) {
        if (newAgentState == CRITICAL || currentOrgState == CRITICAL) {
            return CRITICAL;
        }
        if (newAgentState == WARNING || currentOrgState == WARNING) {
            return WARNING;
        }
        return OK;
    }
}