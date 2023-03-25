package com.example.instagram.Dto.ChatRoom;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomResponseDto {
    private long id;
    private String nickname;
    private String profile_image_url;
    private String last_message;
    private LocalDateTime last_sent_at;

    public ChatRoomResponseDto toDo(ChatRoom chatRoom) {
        return new ChatRoomResponseDto(chatRoom.getId(),
                chatRoom.getUser().getNickname(),
                chatRoom.getUser().getProfile_image_url(),
                chatRoom.getLast_message(),
                chatRoom.getLast_sent_at());
    }
}
