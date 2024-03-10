package com.example.task.mapper;

import com.example.task.controller.request.MessageFilterDto;
import com.example.task.controller.request.MessageRequestDto;
import com.example.task.controller.response.MessageDto;
import com.example.task.model.Message;
import com.example.task.service.request.MessageFilter;
import com.example.task.service.request.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class MappingHelper {

    public MessageRequest toMessageRequest(final MessageRequestDto requestDto) {
        return new MessageRequest(
                requestDto.getName(),
                requestDto.getSurname(),
                requestDto.getMessage()
        );
    }

    public MessageDto toMessageDto(final Message message) {
        return new MessageDto(
                message.getId(),
                message.getName(),
                message.getSurname(),
                message.getMessage()
        );
    }

    public MessageFilter toMessageFilter(final MessageFilterDto filterDto) {
        return new MessageFilter(
                filterDto.getFrom(),
                filterDto.getSize()
        );
    }
}
