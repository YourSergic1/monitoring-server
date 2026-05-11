package github.titandea.service;

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
import java.util.List;

import static github.titandea.enums.AgentState.*;

@Service
@RequiredArgsConstructor
public class AgentStateService {

    private final OrganizationRepository organizationRepository;

    @Transactional
    @Scheduled(fixedRate = 300_000)
    public void changeAgentState() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        List<OrganizationEntity> organizations = organizationRepository.findAll();

        for (OrganizationEntity organization : organizations) {
            AgentState worstOrgState = OK;
            List<AgentEntity> agents = organization.getAgents();

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
            }
            organization.setState(worstOrgState);
        }
    }

    /**
     * Возвращает наихудшее состояние согласно ТЗ:
     * CRITICAL > WARNING > OK
     */
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