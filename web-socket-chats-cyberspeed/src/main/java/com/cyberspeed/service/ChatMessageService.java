package com.cyberspeed.service;

import com.cyberspeed.model.ChatMessage;
import com.cyberspeed.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    ChatMessageService(ChatMessageRepository chatMessageRepository){
        this.chatMessageRepository = chatMessageRepository;
    }
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    public Optional<ChatMessage> findChatMessageById(Long senderId) {
        return chatMessageRepository.findById(senderId);
    }

    public void deleteChatMessage(Long id) {
        chatMessageRepository.deleteById(id);
    }
}


