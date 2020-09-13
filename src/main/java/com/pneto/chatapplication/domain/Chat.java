package com.pneto.chatapplication.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Table(name = "chat",
        indexes = {@Index(name = "participants", columnList = "participants", unique = true)})
public class Chat extends BaseEntity {
    public static String DELIMITER = ",";

    @NotBlank
    private String participants;

    @OneToMany
    private List<Message> messages;

    public Chat() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public static String buildParticipants(String from, String to) {
        return Stream.of(from, to)
                .sorted()
                .collect(Collectors.joining(Chat.DELIMITER));
    }

    public static String normalizeParticipants(String participants) {
        return Stream.of(participants.split(Chat.DELIMITER))
                .map(String::trim)
                .sorted()
                .collect(Collectors.joining(Chat.DELIMITER));
    }
}
