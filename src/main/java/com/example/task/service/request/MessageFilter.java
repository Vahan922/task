package com.example.task.service.request;

import lombok.Data;

@Data
public class MessageFilter {

    private final Integer from;
    private final Integer size;
}
