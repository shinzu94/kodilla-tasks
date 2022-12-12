package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrelloMapper {

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return TrelloBoard.builder()
                .id(trelloBoardDto.getId())
                .name(trelloBoardDto.getName())
                .lists(mapToLists(trelloBoardDto.getLists()))
                .build();
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());

    }

    public TrelloBoardDto mapToBoardDto(final TrelloBoard trelloBoard) {
        return TrelloBoardDto.builder()
                .id(trelloBoard.getId())
                .name(trelloBoard.getName())
                .lists(mapToListDtos(trelloBoard.getLists()))
                .build();
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(this::mapToBoardDto)
                .collect(Collectors.toList());

    }

    public List<TrelloList> mapToLists(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(this::mapToList)
                .collect(Collectors.toList());
    }

    public TrelloList mapToList(TrelloListDto trelloList) {
        return TrelloList.builder()
                .id(trelloList.getId())
                .name(trelloList.getName())
                .isClosed(trelloList.isClosed())
                .build();
    }

    public List<TrelloListDto> mapToListDtos(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(this::mapToListDto)
                .collect(Collectors.toList());
    }

    public TrelloListDto mapToListDto(TrelloList trelloList) {
        return TrelloListDto.builder()
                .id(trelloList.getId())
                .name(trelloList.getName())
                .isClosed(trelloList.isClosed())
                .build();
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
