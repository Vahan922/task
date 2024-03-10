package com.example.task.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageKafkaRequest {

    private String name;
    private String surname;
    private String message;
}
