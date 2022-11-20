package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {
    private TrelloMapper trelloMapper = new TrelloMapper();


    @Test
    void testTrelloBoardDtoToTrelloBoard() {
        //GIVEN
        TrelloListDto trelloListDto = prepareListDto(1, false);
        TrelloListDto trelloListDto2 = prepareListDto(2, true);
        TrelloBoardDto trelloBoardDto = prepareBoardDto(1, List.of(trelloListDto, trelloListDto2));

        TrelloList expectedList = prepareList(1, false);
        TrelloList expectedList2 = prepareList(2, true);
        TrelloBoard expectedBoard = prepareBoard(1, List.of(expectedList, expectedList2));

        //WHEN
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);

        //THEN
        assertEquals(expectedBoard, trelloBoard);
        assertEquals(expectedBoard.getId(), trelloBoard.getId());
        assertEquals(expectedBoard.getName(), trelloBoard.getName());
        assertEquals(2, trelloBoard.getLists().size());
        assertEquals(expectedBoard.getLists(), trelloBoard.getLists());
    }

    @Test
    void testTrelloBoardDtoListToTrelloBoardList() {
        //GIVEN
        List<TrelloBoardDto> trelloBoardDtoList = List.of(
                prepareBoardDto(1, List.of()),
                prepareBoardDto(2, List.of())
        );

        List<TrelloBoard> exceptedBoards = List.of(
                prepareBoard(1, List.of()),
                prepareBoard(2, List.of())
        );

        //WHEN
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        //THEN
        assertEquals(exceptedBoards, trelloBoards);
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void testTrelloBoardToTrelloBoardDto() {
        //GIVEN
        final String boardId = "b1";
        TrelloList trelloList = prepareList(1, false);
        TrelloList trelloList2 = prepareList(2, true);
        TrelloBoard trelloBoard = prepareBoard(1, List.of(trelloList, trelloList2));

        TrelloListDto expectedList = prepareListDto(1, false);
        TrelloListDto expectedList2 = prepareListDto(2, true);
        TrelloBoardDto expectedBoard = prepareBoardDto(1, List.of(expectedList, expectedList2));

        //WHEN
        TrelloBoardDto trelloBoardDto = trelloMapper.mapToBoardDto(trelloBoard);

        //THEN
        assertEquals(expectedBoard, trelloBoardDto);
        assertEquals(expectedBoard.getId(), trelloBoardDto.getId());
        assertEquals(expectedBoard.getName(), trelloBoardDto.getName());
        assertEquals(expectedBoard.getLists(), trelloBoardDto.getLists());
    }

    @Test
    void testTrelloBoardListToTrelloBoardDtoList() {
        //GIVEN
        List<TrelloBoard> trelloBoardList = List.of(
                prepareBoard(1, List.of()),
                prepareBoard(2, List.of())
        );

        List<TrelloBoardDto> exceptedBoards = List.of(
                prepareBoardDto(1, List.of()),
                prepareBoardDto(2, List.of())
        );

        //WHEN
        List<TrelloBoardDto> trelloBoards = trelloMapper.mapToBoardsDto(trelloBoardList);

        //THEN
        assertEquals(exceptedBoards, trelloBoards);
    }

    @Test
    public void testTrelloListDtoToTrelloList() {
        //GIVEN
        TrelloListDto trelloListDto = prepareListDto(1, false);
        TrelloListDto trelloListDto2 = prepareListDto(2, true);

        TrelloList expectedList = prepareList(1, false);
        TrelloList expectedList2 = prepareList(2, true);

        //WHEN
        TrelloList trelloList = trelloMapper.mapToList(trelloListDto);
        TrelloList trelloList2 = trelloMapper.mapToList(trelloListDto2);

        //THEN
        assertEquals(expectedList, trelloList);
        assertEquals(expectedList.getId(), trelloList.getId());
        assertEquals(expectedList.getName(), trelloList.getName());
        assertEquals(expectedList.isClosed(), trelloList.isClosed());

        assertEquals(expectedList2, trelloList2);
        assertEquals(expectedList2.getId(), trelloList2.getId());
        assertEquals(expectedList2.getName(), trelloList2.getName());
        assertEquals(expectedList2.isClosed(), trelloList2.isClosed());
    }

    @Test
    public void testTrelloListDtosToTrelloLists() {
        //GIVEN
        List<TrelloListDto> trelloListDtos = List.of(
                prepareListDto(1, false),
                prepareListDto(2, true)
        );

        List<TrelloList> expectedLists = List.of(
                prepareList(1, false),
                prepareList(2, true)
        );

        //WHEN
        List<TrelloList> trelloLists = trelloMapper.mapToLists(trelloListDtos);

        //THEN
        assertEquals(expectedLists, trelloLists);
    }

    @Test
    public void testTrelloListToTrelloListDto() {
        //GIVEN
        TrelloList trelloList = prepareList(1, false);
        TrelloList trelloList2 = prepareList(2, true);

        TrelloListDto expectedListDto = prepareListDto(1, false);
        TrelloListDto expectedListDto2 = prepareListDto(2, true);

        //WHEN
        TrelloListDto trelloListDto = trelloMapper.mapToListDto(trelloList);
        TrelloListDto trelloListDto2 = trelloMapper.mapToListDto(trelloList2);

        //THEN
        assertEquals(expectedListDto, trelloListDto);
        assertEquals(expectedListDto.getId(), trelloListDto.getId());
        assertEquals(expectedListDto.getName(), trelloListDto.getName());
        assertEquals(expectedListDto.isClosed(), trelloListDto.isClosed());

        assertEquals(expectedListDto2, trelloListDto2);
        assertEquals(expectedListDto2.getId(), trelloListDto2.getId());
        assertEquals(expectedListDto2.getName(), trelloListDto2.getName());
        assertEquals(expectedListDto2.isClosed(), trelloListDto2.isClosed());
    }

    @Test
    public void testTrelloListToTrelloListsDtos() {
        //GIVEN
        List<TrelloList> trelloLists = List.of(
                prepareList(1, false),
                prepareList(2, true)
        );
        List<TrelloListDto> expectedLists = List.of(
                prepareListDto(1, false),
                prepareListDto(2, true)
        );

        //WHEN
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDtos(trelloLists);

        //THEN
        assertEquals(expectedLists, trelloListDtos);
    }

    @Test
    public void testTrelloCardDtoToTrelloCard() {
        //GIVEN
        TrelloCardDto trelloCardDto = prepareCardDto();
        TrelloCard expectedCard = prepareCard();

        //WHEN
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //THEN
        assertEquals(expectedCard, trelloCard);
        assertEquals(expectedCard.getListId(), trelloCard.getListId());
        assertEquals(expectedCard.getName(), trelloCard.getName());
        assertEquals(expectedCard.getDescription(), trelloCard.getDescription());
        assertEquals(expectedCard.getPos(), trelloCard.getPos());
    }

    @Test
    public void testTrelloCardToTrelloCardDto() {
        //GIVEN
        TrelloCard trelloCardDto = prepareCard();

        TrelloCardDto expectedCard = prepareCardDto();

        //WHEN
        TrelloCardDto trelloCard = trelloMapper.mapToCardDto(trelloCardDto);

        //THEN
        assertEquals(expectedCard, trelloCard);
        assertEquals(expectedCard.getListId(), trelloCard.getListId());
        assertEquals(expectedCard.getName(), trelloCard.getName());
        assertEquals(expectedCard.getDescription(), trelloCard.getDescription());
        assertEquals(expectedCard.getPos(), trelloCard.getPos());
    }

    private TrelloBoardDto prepareBoardDto(int number, List<TrelloListDto> trelloListDtos) {
        return TrelloBoardDto.builder()
                .id(prepareBoardId(number))
                .name(prepareBoardName(number))
                .lists(trelloListDtos)
                .build();
    }

    private TrelloBoard prepareBoard(int number, List<TrelloList> trelloLists) {
        return TrelloBoard.builder()
                .id(prepareBoardId(number))
                .name(prepareBoardName(number))
                .lists(trelloLists)
                .build();
    }

    private TrelloListDto prepareListDto(int number, boolean close) {
        return TrelloListDto.builder()
                .id(prepareListId(number))
                .name(prepareListName(number))
                .isClosed(close)
                .build();
    }

    private TrelloList prepareList(int number, boolean close) {
        return TrelloList.builder()
                .id(prepareListId(number))
                .name(prepareListName(number))
                .isClosed(close)
                .build();
    }


    private TrelloCard prepareCard() {
        return TrelloCard.builder()
                .listId("list")
                .name("testowy")
                .description("Description")
                .pos("Top")
                .build();
    }

    private TrelloCardDto prepareCardDto() {
        return TrelloCardDto.builder()
                .listId("list")
                .name("testowy")
                .description("Description")
                .pos("Top")
                .build();
    }


    private String prepareBoardId(int number) {
        return "b" + number;
    }

    private String prepareBoardName(int number) {
        return "testowy" + number;
    }

    private String prepareListId(int number) {
        return "l" + number;
    }

    private String prepareListName(int number) {
        return "testowa" + number;
    }
}
