package com.example.task.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.task.kafka.config.KafkaTopicConfig.MESSAGE;

@Slf4j
@Component
public class KafkaMessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(final String message) {
        log.debug("Producing message:{}...", message);

        kafkaTemplate.send(MESSAGE, message);

        log.debug("Done producing message:{}.", message);
    }
}
