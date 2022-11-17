package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class TaskDto {
    private final Long id;
    private final String title;
    private final String content;
}
