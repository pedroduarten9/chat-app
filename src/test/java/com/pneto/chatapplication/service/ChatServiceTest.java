package com.pneto.chatapplication.service;

import com.pneto.chatapplication.domain.Chat;
import com.pneto.chatapplication.domain.Message;
import com.pneto.chatapplication.dto.ChatDto;
import com.pneto.chatapplication.dto.MessageDto;
import com.pneto.chatapplication.repository.ChatRepository;
import com.pneto.chatapplication.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private ChatRepository chatRepository;

    @MockBean
    private MessageRepository messageRepository;


    @Test
    public void testSaveMessageToChat() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSender("part1");
        messageDto.setDestination("part2");
        messageDto.setMessage("sample message");

        Chat chat = new Chat();
        chat.setParticipants(Chat.buildParticipants(messageDto.getDestination(), messageDto.getSender()));

        when(chatRepository.findByParticipants(any())).thenReturn(Optional.of(chat));

        chatService.saveMessageToChat(messageDto);

        Message message = chat.getMessages().get(0);
        assertThat(message.getFrom()).isEqualTo(messageDto.getSender());
        assertThat(message.getTo()).isEqualTo(messageDto.getDestination());
        assertThat(message.getMessagePayload()).isEqualTo(messageDto.getMessage());
    }


    @Test
    public void testGetChat() {
        String participants = "part1,part2";

        Chat chat = new Chat();
        chat.setParticipants(participants);

        when(chatRepository.findByParticipants(any())).thenReturn(Optional.of(chat));

        ChatDto chatDto = chatService.getChat(participants);

        assertThat(chatDto.getParticipants()).isEqualTo(Arrays.asList(participants.split(Chat.DELIMITER)));

    }
}
