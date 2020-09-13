package com.pneto.chatapplication.converter;

import com.pneto.chatapplication.domain.Message;
import com.pneto.chatapplication.dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public Message fromDto(MessageDto messageDto) {
        Message message = new Message();
        message.setTo(messageDto.getDestination());
        message.setFrom(messageDto.getSender());
        message.setMessagePayload(messageDto.getMessage());
        return message;
    }

    public MessageDto toDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setSender(message.getFrom());
        messageDto.setDestination(message.getTo());
        messageDto.setTime(message.getWhenCreated());
        messageDto.setMessage(message.getMessagePayload());
        return messageDto;
    }
}
