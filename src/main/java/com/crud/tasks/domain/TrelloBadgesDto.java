package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrelloBadgesDto {
    private int votes;

    private TrelloAttachmentsByTypeDto attachmentsByType;
}
