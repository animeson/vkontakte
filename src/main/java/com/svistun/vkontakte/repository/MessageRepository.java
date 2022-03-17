package com.svistun.vkontakte.repository;

import com.svistun.vkontakte.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getMessagesBySenderByIdOrRecipientById(Long senderById, Long recipientById);
}
