package com.pneto.chatapplication.repository;

import com.pneto.chatapplication.domain.Chat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChatRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void testFindByParticipants() {
        Chat chat = new Chat();
        chat.setParticipants("part1,part2");

        testEntityManager.persist(chat);
        testEntityManager.flush();

        Optional<Chat> found = chatRepository.findByParticipants("part1,part2");

        assertThat(found).isNotEmpty();
    }

    @Test
    public void testFindByParticipantsEmpty() {
        Chat chat = new Chat();
        chat.setParticipants("part1,part2");

        testEntityManager.persist(chat);
        testEntityManager.flush();

        Optional<Chat> found = chatRepository.findByParticipants("part3,part1");

        assertThat(found).isEmpty();
    }
}
