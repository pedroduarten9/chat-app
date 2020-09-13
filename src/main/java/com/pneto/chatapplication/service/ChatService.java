package com.pneto.chatapplication.service;

import com.pneto.chatapplication.dto.ChatDto;
import com.pneto.chatapplication.dto.MessageDto;

public interface ChatService {

    void saveMessageToChat(MessageDto messageDto);

    ChatDto getChat(String participants);
}
