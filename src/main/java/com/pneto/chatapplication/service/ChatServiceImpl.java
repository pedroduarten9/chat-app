package com.pneto.chatapplication.service;

import com.pneto.chatapplication.converter.ChatConverter;
import com.pneto.chatapplication.converter.MessageConverter;
import com.pneto.chatapplication.domain.Chat;
import com.pneto.chatapplication.domain.Message;
import com.pneto.chatapplication.dto.ChatDto;
import com.pneto.chatapplication.dto.MessageDto;
import com.pneto.chatapplication.error.ErrorCode;
import com.pneto.chatapplication.error.exception.NotFoundException;
import com.pneto.chatapplication.repository.ChatRepository;
import com.pneto.chatapplication.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;
    private final ChatConverter chatConverter;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository,
                           MessageConverter messageConverter, ChatConverter chatConverter) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
        this.chatConverter = chatConverter;
    }

    @Transactional
    public void saveMessageToChat(MessageDto messageDto) {
        String participants = Chat.buildParticipants(messageDto.getDestination(), messageDto.getSender());

        Chat chat = chatRepository.findByParticipants(participants)
                .orElseGet(() -> {
                    log.debug("Chat with participants {} was not found, creating a new one", participants);
                    Chat newChat = new Chat();
                    newChat.setParticipants(participants);
                    return newChat;
                });

        Message message = messageConverter.fromDto(messageDto);
        chat.addMessage(message);

        messageRepository.save(message);
        chatRepository.save(chat);
        log.debug("Successful saved message:{}", message);
    }

    public ChatDto getChat(String participants) {
        String normalizedParticipants = Chat.normalizeParticipants(participants);
        Chat chat = chatRepository.findByParticipants(normalizedParticipants)
                .orElseThrow(() -> {
                    log.warn("chat with participants {} was not found", participants);
                    return new NotFoundException(ErrorCode.NOT_FOUND_CHAT);
                });

        log.debug("Success getting chat {} from repository", chat);
        ChatDto chatDto = chatConverter.toDto(chat);
        log.debug("Success converting chat to dto: {}", chatDto);
        return chatDto;
    }
}
