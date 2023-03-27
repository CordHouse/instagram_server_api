package com.example.instagram.Repository.ChatRoom;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByTargetAndUserOrUserAndTarget(long id, User user, User user1, long id1);
    List<ChatRoom> findAllByUserOrTarget(User user, long id);
}
