package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
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

    private final CompanyConfig companyConfig;

    private final AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("admin_name", adminConfig.getAdminMail());
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost");
        context.setVariable("button", "Visit website");
        context.setVariable("company_name", companyConfig.getName());
        context.setVariable("company_email", companyConfig.getEmail());
        context.setVariable("company_phone", companyConfig.getPhone());
        context.setVariable("goodbye_message", "See you soon.");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
