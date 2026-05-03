package github.titandea.kafka;

import github.titandea.dto.InitInfo;
import github.titandea.service.AgentInitializationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitKafkaListener {

    private final AgentInitializationService agentInitializationService;

    @KafkaListener(topics = "${kafka.init-topic}",
            groupId = "init-consumer-group",
            containerFactory = "initContainerFactory")
    public void consume(InitInfo initInfo) {
        agentInitializationService.initAgent(initInfo);
    }
}
