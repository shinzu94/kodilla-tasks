package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Getter
@AllArgsConstructor
@Builder
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;
}