package com.pneto.chatapplication.controller;

import com.pneto.chatapplication.dto.MessageDto;
import com.pneto.chatapplication.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatService chatService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/room")
    public void sendMessage(@Payload @NotNull @Valid MessageDto messageDto) {
        chatService.saveMessageToChat(messageDto);
        simpMessagingTemplate.convertAndSendToUser(messageDto.getDestination(), "/queue/messages", messageDto);
    }
}
