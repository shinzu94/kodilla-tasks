package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    public static final String TEST_EMAIL = "test@test.test";

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private TrelloBadgesDto trelloBadgesDto;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchListWithElement() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = List.of(
                TrelloBoardDto.builder()
                        .id("b1")
                        .name("name")
                        .lists(List.of())
                        .build()
        );

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //WHEN
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();

        //THEN
        assertEquals(trelloBoardDtoList, trelloBoards);
    }

    @Test
    public void shouldCreateCard() {
        //Given
        String receiverEmail = "test@test.test";
        TrelloCardDto trelloCardDto = prepareTrelloCardDto();
        CreatedTrelloCardDto createdTrelloCardDto = CreatedTrelloCardDto.builder()
                .id("1")
                .shortUrl("url")
                .name("name")
                .badges(trelloBadgesDto)
                .build();

        Mail mail = prepareMail(receiverEmail);

        when(adminConfig.getAdminMail()).thenReturn(receiverEmail);
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);


        //WHEN
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //THEN
        verify(emailService, times(1)).send(mail);
        assertEquals(createdTrelloCardDto, result);
    }

    @Test
    public void shouldHaveProblemWithCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = prepareTrelloCardDto();
        Mail mail = prepareMail(TEST_EMAIL);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);

        //WHEN
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //THEN
        verify(emailService, times(0)).send(mail);
        assertNull(result);
    }

    private TrelloCardDto prepareTrelloCardDto() {
        return TrelloCardDto.builder()
                .name("none")
                .pos("none")
                .description("none")
                .listId("none")
                .build();
    }

    private Mail prepareMail(String receiverEmail) {
        return Mail.builder()
                .receiverEmail(receiverEmail)
                .subject("New card")
                .message("New card: none has been created on your Trello account")
                .build();
    }
}
