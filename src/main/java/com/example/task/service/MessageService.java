package com.example.task.service;

import com.example.task.kafka.producer.KafkaMessageProducer;
import com.example.task.model.Message;
import com.example.task.repository.MessageRepository;
import com.example.task.service.request.MessageFilter;
import com.example.task.service.request.MessageRequest;
import com.example.task.service.response.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    private final KafkaMessageProducer kafkaMessageProducer;
    private final MessageRepository repository;

    public MessageService(final KafkaMessageProducer kafkaMessageProducer, final MessageRepository repository) {
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.repository = repository;
    }

    public void produce(final MessageRequest request) {
        log.debug("Producing message request:{}...", request);

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaMessageProducer.produce(objectMapper.writeValueAsString(request));
        } catch (final JsonProcessingException ex) {
            log.error("Error while parsing object to json: {}", ex.getMessage());
        }

        log.debug("Done producing message request:{}.", request);
    }

    public Message getById(final long id) {
        log.debug("Getting message by id:{}...", id);

        final Message result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Message could not be found by id:'%s'", id))
        );

        log.debug("Done getting message by id:{}.", id);
        return result;
    }

    public void save(final MessageRequest request) {
        log.debug("Saving message:{}...", request);

        final Message message = accumulateMessage(request);
        repository.save(message);

        log.debug("Done saving message:{}.", request);
    }

    public PageResponse<Message> getList(final MessageFilter filter) {
        log.debug("Getting messageList by filter:{}...", filter);

        final Page<Message> pageResult = repository.findAll(PageRequest.of(filter.getFrom(), filter.getSize()));
        final PageResponse<Message> pageResponse = new PageResponse<>(
                pageResult.getTotalElements(),
                pageResult.getContent()
        );

        log.debug("Done getting messageList by filter:{}...", filter);
        return pageResponse;
    }

    private static Message accumulateMessage(MessageRequest request) {
        final Message message = new Message();
        message.setName(request.getName());
        message.setSurname(request.getSurname());
        message.setMessage(request.getMessage());
        return message;
    }
}
