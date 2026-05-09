package github.titandea.config;

import github.titandea.dto.create.Agent;
import github.titandea.dto.create.SystemMetrics;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация Kafka-консьюмеров для обработки сообщений SystemMetrics и Agent.
 * Настраивает фабрики потребителей, JSON-десериализацию, группы потребителей.
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${KAFKA_CONSUMER_METRICS_GROUP_ID")
    private String metricsGroupId;

    @Value("${KAFKA_CONSUMER_AGENT_GROUP_ID}")
    private String agentGroupId;

    @Bean
    public ConsumerFactory<String, SystemMetrics> metricsConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, metricsGroupId);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, SystemMetrics.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "github.titandea.dto.*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SystemMetrics.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SystemMetrics> metricsContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, SystemMetrics>();
        factory.setConsumerFactory(metricsConsumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Agent> initConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, agentGroupId);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Agent.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "github.titandea.dto.*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Agent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Agent> initContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Agent>();
        factory.setConsumerFactory(initConsumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }
}