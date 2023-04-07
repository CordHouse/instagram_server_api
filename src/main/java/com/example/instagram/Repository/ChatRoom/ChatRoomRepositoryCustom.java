package com.example.instagram.Repository.ChatRoom;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatRoomRepositoryCustom {
    Page<ChatRoom> findByQChatRoomQHostOrQTarget(Pageable pageable, User host, User target, long cursorId);
}
