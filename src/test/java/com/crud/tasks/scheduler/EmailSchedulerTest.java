package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EmailSchedulerTest {

    public static final String TEST_EMAIL = "test@test.tv";

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    void sendInformationForOneTaskEmail() {
        //GIVEN
        final long size = 1;
        final String email = TEST_EMAIL;
        final String message = "Currently in database you got: " + size + " task";

        Mail mail = new Mail(
                email,
                "Tasks: Once a day email",
                message,
                null
        );

        when(taskRepository.count()).thenReturn(size);
        when(adminConfig.getAdminMail()).thenReturn(email);

        //WHEN
        emailScheduler.sendInformationEmail();

        //THEN
        verify(simpleEmailService, times(1)).send(mail);
    }

    @Test
    void sendInformationForManyTaskEmail() {
        //GIVEN
        final long size = 2;
        final String email = TEST_EMAIL;
        final String message = "Currently in database you got: " + size + " tasks";

        Mail mail = new Mail(
                email,
                "Tasks: Once a day email",
                message,
                null
        );

        when(taskRepository.count()).thenReturn(size);
        when(adminConfig.getAdminMail()).thenReturn(email);

        //WHEN
        emailScheduler.sendInformationEmail();

        //THEN
        verify(simpleEmailService, times(1)).send(mail);
    }
}
