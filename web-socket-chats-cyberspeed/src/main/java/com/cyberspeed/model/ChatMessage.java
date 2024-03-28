package com.cyberspeed.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ChatMessage {
    @Id
    private String id;
    private String content;
    private String sender;
    private MessageAction messageAction;
}


