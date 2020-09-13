package com.pneto.chatapplication.converter;

import com.pneto.chatapplication.domain.Chat;
import com.pneto.chatapplication.dto.ChatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ChatConverter {

    private final MessageConverter messageConverter;

    @Autowired
    public ChatConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public ChatDto toDto(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setParticipants(Arrays.asList(chat.getParticipants().split(Chat.DELIMITER)));
        chatDto.setMessages(chat.getMessages().stream()
                .map(messageConverter::toDto)
                .collect(Collectors.toList()));
        return chatDto;
    }
}
