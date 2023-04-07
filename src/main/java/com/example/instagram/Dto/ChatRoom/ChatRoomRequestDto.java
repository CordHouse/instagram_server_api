package com.example.instagram.Dto.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomRequestDto {
    @Nullable
    private long cursor;
}
