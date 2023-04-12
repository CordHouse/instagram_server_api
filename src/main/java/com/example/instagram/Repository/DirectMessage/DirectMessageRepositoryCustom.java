package com.example.instagram.Repository.DirectMessage;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.DirectMessage.DirectMessage;
import com.example.instagram.Entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectMessageRepositoryCustom {
    Page<DirectMessage> findQChatRoomAndQSenderOrQReceiver(Pageable pageable, ChatRoom chatRoom, User user, long cursorId);
}
