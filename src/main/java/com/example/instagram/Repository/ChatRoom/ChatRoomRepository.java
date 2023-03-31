package com.example.instagram.Repository.ChatRoom;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByTargetAndHostOrHostAndTarget(User targetUser, User hostUser, User hostUser1, User targetUser1);
    List<ChatRoom> findAllByHostOrTarget(User hostUser, User targetUser);
}
