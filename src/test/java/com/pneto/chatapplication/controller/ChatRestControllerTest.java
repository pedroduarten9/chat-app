package com.pneto.chatapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneto.chatapplication.dto.ChatDto;
import com.pneto.chatapplication.error.ErrorCode;
import com.pneto.chatapplication.error.exception.NotFoundException;
import com.pneto.chatapplication.service.ChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatRestController.class)
public class ChatRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChatService chatService;

    @Test
    public void testGetChatByParticipants() throws Exception {
        String participants = "part1,part2";

        when(chatService.getChat(any()))
                .thenReturn(new ChatDto());

        mvc.perform(
                get("/chat/participants/" + participants))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testGetChatByParticipantsNotFound() throws Exception {
        String participants = "part1,part2";

        when(chatService.getChat(any()))
                .thenThrow(new NotFoundException(ErrorCode.NOT_FOUND_CHAT));

        mvc.perform(
                get("/chat/participants/" + participants))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}