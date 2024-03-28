package com.cyberspeed.controller;

import com.cyberspeed.model.ChatMessage;
import com.cyberspeed.service.ChatMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ChatMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatMessageService chatMessageService;


    @Test
    public void givenListOfChatMessages_whenGetAllChatMessages_thenReturnChatMessageList() throws Exception {
        // given - precondition or setup
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(ChatMessage.builder().sender("Ramesh").content("Fadatare").build());
        chatMessages.add(ChatMessage.builder().sender("Tony").content("Stark").build());
        given(chatMessageService.findAllChatMessages()).willReturn(chatMessages);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/messages"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(chatMessages.size())));

    }


    @Test
    public void givenInvalidChatMessageId_whenGetChatMessageById_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        long chatMessageId = 1L;
        ChatMessage chatMessage = ChatMessage.builder()
                .sender("Ramesh")
                .content("Web Sockets")
                .build();
        given(chatMessageService.findChatMessageById(chatMessageId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/message/{chatMessageId}", chatMessageId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void givenChatMessageId_whenDeleteChatMessage_thenReturn200() throws Exception {
        // given - precondition or setup
        long chatMessageId = 1L;
        willDoNothing().given(chatMessageService).deleteChatMessage(chatMessageId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/delete/message/{chatMessageId}", chatMessageId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}