package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));

//        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
//        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
//        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
//        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void createTrelloCardShouldCreateCard() {
        // Given
        TrelloCardDto cardDto = TrelloCardDto.builder()
                .name("te")
                .description("tes")
                .listId("mo")
                .pos("TOP")
                .build();
        TrelloCard card = TrelloCard.builder()
                .name("te")
                .description("tes")
                .listId("mo")
                .pos("TOP")
                .build();
        CreatedTrelloCardDto expectedCreatedTrelloCardDto = CreatedTrelloCardDto.builder()
                .id("te")
                .name("te")
                .build();
        when(trelloMapper.mapToCard(cardDto)).thenReturn(card);
        when(trelloService.createTrelloCard(cardDto)).thenReturn(expectedCreatedTrelloCardDto);

        // When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(cardDto);

        // Then
        assertEquals(expectedCreatedTrelloCardDto, createdTrelloCardDto);
        verify(trelloValidator, times(1)).validateCard(card);
        verify(trelloService, times(1)).createTrelloCard(cardDto);
    }
}
