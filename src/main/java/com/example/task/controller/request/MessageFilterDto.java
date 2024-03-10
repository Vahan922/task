package com.example.task.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageFilterDto {

    @JsonProperty("from")
    private Integer from;

    @JsonProperty("size")
    private Integer size;
}
