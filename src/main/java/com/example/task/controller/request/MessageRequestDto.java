package com.example.task.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageRequestDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @NotEmpty
    @JsonProperty("message")
    private String message;
}
