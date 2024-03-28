package com.cyberspeed.controller;

import com.cyberspeed.model.ChatMessage;
import com.cyberspeed.service.ChatMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage chat(@Payload ChatMessage chatMessage) {
        return chatMessageService.save(chatMessage);
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/message/{senderId}")
    ResponseEntity<Optional<ChatMessage>> findChatMessage(@PathVariable("senderId") Long senderId) {
        return ResponseEntity.ok(chatMessageService.findChatMessageById(senderId));
    }

    @GetMapping("/messages")
    ResponseEntity<List<ChatMessage>> findAllChatMessages() {
        return ResponseEntity.ok(chatMessageService.findAllChatMessages());
    }

    @DeleteMapping("delete/message/{id}")
    public void deleteChatMessage(@PathVariable("id") Long id) {
        chatMessageService.deleteChatMessage(id);
    }
}