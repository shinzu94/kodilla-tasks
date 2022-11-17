package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class TrelloMapper {

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return TrelloBoard.builder()
                .id(trelloBoardDto.getId())
                .name(trelloBoardDto.getName())
                .lists(mapToList(trelloBoardDto.getLists()))
                .build();
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());

    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard ->
                        TrelloBoardDto.builder()
                                .id(trelloBoard.getId())
                                .name(trelloBoard.getName())
                                .lists(mapToListDto(trelloBoard.getLists()))
                                .build()
                )
                .collect(Collectors.toList());

    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> TrelloList.builder()
                        .id(trelloList.getId())
                        .name(trelloList.getName())
                        .isClosed(trelloList.isClosed())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> TrelloListDto.builder()
                        .id(trelloList.getId())
                        .name(trelloList.getName())
                        .isClosed(trelloList.isClosed())
                        .build())
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return TrelloCardDto.builder()
                .name(trelloCard.getName())
                .description(trelloCard.getDescription())
                .pos(trelloCard.getPos())
                .listId(trelloCard.getListId())
                .build();
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return TrelloCard.builder()
                .name(trelloCardDto.getName())
                .description(trelloCardDto.getDescription())
                .pos(trelloCardDto.getPos())
                .listId(trelloCardDto.getListId())
                .build();
    }
}
