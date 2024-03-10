package com.example.task.controller;

import com.example.task.controller.request.MessageFilterDto;
import com.example.task.controller.request.MessageRequestDto;
import com.example.task.controller.response.MessageDto;
import com.example.task.controller.response.PageResponseDto;
import com.example.task.mapper.MappingHelper;
import com.example.task.model.Message;
import com.example.task.service.MessageService;
import com.example.task.service.request.MessageFilter;
import com.example.task.service.request.MessageRequest;
import com.example.task.service.response.PageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final MappingHelper mappingHelper;

    public MessageController(final MessageService messageService, final MappingHelper mappingHelper) {
        this.messageService = messageService;
        this.mappingHelper = mappingHelper;
    }

    @PostMapping("/produce")
    public void produce(@RequestBody @NotNull @Valid MessageRequestDto requestDto) {
        log.debug("Producing message requestDto:{}...", requestDto);

        final MessageRequest request = mappingHelper.toMessageRequest(requestDto);
        messageService.produce(request);

        log.info("Done producing message requestDto:{}.", requestDto);
    }

    @GetMapping("/{id}")
    public MessageDto getById(@PathVariable("id") long id) {
        log.debug("Getting message by id:{}...", id);

        final Message result = messageService.getById(id);
        final MessageDto resultDto = mappingHelper.toMessageDto(result);

        log.info("Done getting message by id:{}.", id);
        return resultDto;
    }

    @PostMapping("/list")
    public PageResponseDto<MessageDto> getList(@RequestBody @NotNull MessageFilterDto filterDto) {
        log.debug("Getting messageList by filterDto:{}...", filterDto);

        final MessageFilter filter = mappingHelper.toMessageFilter(filterDto);
        final PageResponse<Message> pageResponse = messageService.getList(filter);
        final List<MessageDto> resultListDto = pageResponse.getMessages().stream()
                .map(mappingHelper::toMessageDto)
                .toList();
        final PageResponseDto<MessageDto> pageResponseDto = new PageResponseDto<>(
                pageResponse.getTotalCount(),
                resultListDto
        );

        log.info("Done getting messageList by filterDto:{}.", filterDto);
        return pageResponseDto;
    }
}
