package com.pneto.chatapplication.controller;

import com.pneto.chatapplication.dto.ChatDto;
import com.pneto.chatapplication.service.ChatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatRestController {

    private final ChatService chatService;

    @Autowired
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @ApiOperation("Get Chat by participants")
    @GetMapping("/participants/{participants}")
    public ResponseEntity<ChatDto> getChatByParticipants(@PathVariable String participants) {
        return ResponseEntity.ok(chatService.getChat(participants));
    }
}
