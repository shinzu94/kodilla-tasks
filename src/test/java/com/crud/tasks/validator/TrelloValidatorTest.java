package com.crud.tasks.validator;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.crud.tasks.TestLogAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    void shouldValidateTestingCardTest() {
        //GIVEN
        TestLogAppender testLogAppender = new TestLogAppender();
        Logger log = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        log.addAppender(testLogAppender);

        TrelloCard trelloCard = TrelloCard.builder()
                .name("test card")
                .listId("3")
                .pos("TOP")
                .description("description")
                .build();

        //WHEN
        testLogAppender.start();
        trelloValidator.validateCard(trelloCard);
        ILoggingEvent lastLoggedEvent = testLogAppender.getLastLoggedEvent();

        //THEN
        assertEquals("Someone is testing my application!", lastLoggedEvent.getFormattedMessage());
    }

    @Test
    void shouldValidateProperCardTest() {
        //GIVEN
        TestLogAppender testLogAppender = new TestLogAppender();
        Logger log = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        log.addAppender(testLogAppender);

        TrelloCard trelloCard = TrelloCard.builder()
                .name("tes card")
                .listId("3")
                .pos("TOP")
                .description("description")
                .build();

        //WHEN
        testLogAppender.start();
        trelloValidator.validateCard(trelloCard);
        ILoggingEvent lastLoggedEvent = testLogAppender.getLastLoggedEvent();

        //THEN
        assertEquals("Seems that my application is used in proper way.", lastLoggedEvent.getFormattedMessage());
    }


    @Test
    void shouldValidateTrelloBoards() {
        //GIVEN
        TrelloBoard correctBoard = TrelloBoard.builder().name("tes43").build();
        final List<TrelloBoard> trelloBoards = List.of(
                TrelloBoard.builder().name("test").build(),
                correctBoard
        );
        final List<TrelloBoard> expectedTrelloBoards = List.of(
                correctBoard
        );
        //WHEN
        final List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //THEN
        assertEquals(expectedTrelloBoards, result);
    }
}
