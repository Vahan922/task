package com.example.task.kafka.consumer;

import com.example.task.kafka.model.MessageKafkaRequest;
import com.example.task.service.MessageService;
import com.example.task.service.request.MessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.task.kafka.config.KafkaTopicConfig.MESSAGE;

@Slf4j
@Component
public class KafkaMessageConsumer {

    private final MessageService messageService;

    public KafkaMessageConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = MESSAGE, groupId = "groupId")
    public void consume(final String message) {
        log.debug("Consuming message:{}...", message);

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final MessageKafkaRequest kafkaRequest = objectMapper.readValue(message, MessageKafkaRequest.class);
            final MessageRequest request = accumulateMessageRequest(kafkaRequest);
            messageService.save(request);

        } catch (final JsonProcessingException ex) {
            log.error("Error while parsing json to object: {}", ex.getMessage());
        }
        log.info("Done consuming message: {}.", message);
    }

    private static MessageRequest accumulateMessageRequest(MessageKafkaRequest kafkaRequest) {
        return new MessageRequest(
                kafkaRequest.getName(),
                kafkaRequest.getSurname(),
                kafkaRequest.getMessage()
        );
    }
}
