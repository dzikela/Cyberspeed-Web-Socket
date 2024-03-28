package com.cyberspeed.repository;

import com.cyberspeed.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, Long> {
}
