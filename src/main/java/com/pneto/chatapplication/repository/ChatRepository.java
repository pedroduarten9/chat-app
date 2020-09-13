package com.pneto.chatapplication.repository;

import com.pneto.chatapplication.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    Optional<Chat> findByParticipants(String participants);
}
