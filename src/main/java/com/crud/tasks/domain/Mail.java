package com.crud.tasks.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class Mail {
    private final String receiverEmail;
    private final String subject;
    private final String message;
    private Optional<String> toCc;

    @Builder
    public Mail(String receiverEmail, String subject, String message, Optional<String> toCc) {
        this.receiverEmail = receiverEmail;
        this.subject = subject;
        this.message = message;
        this.toCc = toCc != null ? toCc : Optional.empty();
    }
}