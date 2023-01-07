package com.crud.tasks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailCreatorService {

    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost");
        context.setVariable("button", "Visit website");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
