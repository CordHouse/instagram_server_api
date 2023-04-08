package com.example.instagram.Dto.DirectMessage;

import com.example.instagram.Dto.ChatRoom.ChatRoomResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomInfoResponseDto {
    private List<ChatRoomResponseDto> chatRoomResponseDto;

    public ChatRoomInfoResponseDto toDo(List<ChatRoomResponseDto> chatRoomResponseDto) {
        return new ChatRoomInfoResponseDto(chatRoomResponseDto);
    }
}
