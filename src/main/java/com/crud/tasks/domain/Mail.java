package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@AllArgsConstructor
@Builder
public class Mail {

    private String receiverEmail;

    private String subject;

    private String message;

    @Builder.Default
    private Optional<String> toCc = Optional.empty();
}
