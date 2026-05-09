package github.titandea.kafka;

import github.titandea.dto.create.Agent;
import github.titandea.service.AgentInitializationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Инициализация кафка слушателя для информации об инициализации агента.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InitKafkaListener {

    private final AgentInitializationService agentInitializationService;

    @KafkaListener(topics = "${kafka.init-topic}",
            groupId = "init-consumer-group",
            containerFactory = "initContainerFactory")
    public void consume(Agent agent) {
        agentInitializationService.initAgent(agent);
    }
}
