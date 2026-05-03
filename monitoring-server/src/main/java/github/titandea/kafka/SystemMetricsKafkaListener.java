package github.titandea.kafka;

import github.titandea.dto.SystemMetrics;
import github.titandea.service.MetricsIngestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SystemMetricsKafkaListener {

    private final MetricsIngestionService metricsIngestionService;

    @KafkaListener(topics = "${kafka.metrics-topic}",
            groupId = "metrics-consumer-group",
            containerFactory = "metricsContainerFactory")
    public void consume(SystemMetrics dto) {
        metricsIngestionService.processAndSave(dto);
    }
}