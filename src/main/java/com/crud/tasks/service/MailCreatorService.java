package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService {

    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    private final CompanyConfig companyConfig;

    private final AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("admin_name", adminConfig.getAdminMail());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost");
        context.setVariable("button", "Visit website");
        context.setVariable("company_name", companyConfig.getName());
        context.setVariable("company_email", companyConfig.getEmail());
        context.setVariable("company_phone", companyConfig.getPhone());
        context.setVariable("goodbye_message", "See you soon.");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
