package com.example.task.service.request;

import lombok.Data;

@Data
public class MessageRequest {

    private final String name;
    private final String surname;
    private final String message;
}
