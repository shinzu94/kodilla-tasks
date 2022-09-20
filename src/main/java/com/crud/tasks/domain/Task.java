package com.crud.tasks.domain;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private String content;
}
