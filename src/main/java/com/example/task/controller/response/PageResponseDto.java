package com.example.task.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponseDto<T> {

    @JsonProperty("totalCount")
    private Long totalCount;

    @JsonProperty("messages")
    private List<T> messages;
}
