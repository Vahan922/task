package com.example.task.service.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

    private final long totalCount;
    private final List<T> messages;
}
