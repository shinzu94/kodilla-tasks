package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TrelloBadgesDto {
    private int votes;

    private TrelloAttachmentsByTypeDto attachmentsByType;
}
