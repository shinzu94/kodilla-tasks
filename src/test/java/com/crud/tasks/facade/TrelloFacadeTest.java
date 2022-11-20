package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;
//
//    @Mock
//    private TrelloService trelloService;
//
//    @Mock
//    private TrelloValidator trelloValidator;
//
//    @Mock
//    private TrelloMapper trelloMapper;

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
}
