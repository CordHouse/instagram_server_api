package com.example.instagram.Repository.DirectMessage;

import com.example.instagram.Entity.DirectMessage.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
    List<DirectMessage> findAllByChatRoom_Id(long id);
}
