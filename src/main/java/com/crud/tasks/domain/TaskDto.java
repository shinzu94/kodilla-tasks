package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TaskDto {
    private final Long id;
    private final String title;
    private final String content;
}
