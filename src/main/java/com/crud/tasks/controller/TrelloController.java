package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@Slf4j
public class TrelloController {
    private final TrelloService trelloService;
    private final MailProperties mailProperties;
    private final SimpleEmailService simpleEmailService;

    @GetMapping("test")
    public String getTest() {
        simpleEmailService.send(Mail.builder()
                .receiverEmail("oskar.sek94@gmail.com")
                .subject("Test")
                .message("test")
                .toCc(Optional.of("test"))
                .build());

        return mailProperties.getUsername() + " - " + mailProperties.getPassword();
    }

    @GetMapping("boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();

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
        return trelloService.createTrelloCard(trelloCardDto);
    }
}