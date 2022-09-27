package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@Slf4j
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards
                .forEach(trelloBoardDto -> {
                    log.info("{} - {}", trelloBoardDto.getId(), trelloBoardDto.getName());
                    log.info("This board contains lists: ");
                    trelloBoardDto.getLists().forEach(trelloList -> {
                        log.info("{} - {} - {}", trelloList.getName(), trelloList.getId(), trelloList.isClosed());
                    });
                });

        return trelloBoards;
    }

    @PostMapping("cards")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}