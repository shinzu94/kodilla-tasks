package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    private final SimpleEmailService simpleEmailService;

    private final TaskRepository taskRepository;

    private final AdminConfig adminConfig;

    private static String getMessage(long size) {
        return new StringBuilder()
                .append("Currently in database you got: ")
                .append(size)
                .append(size == 1 ? " task" : " tasks")
                .toString();
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        getMessage(taskRepository.count()),
                        null
                )
        );
    }
}
