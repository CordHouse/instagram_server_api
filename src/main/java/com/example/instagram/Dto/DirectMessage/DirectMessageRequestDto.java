package com.example.instagram.Dto.DirectMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessageRequestDto {
    @NotNull(message = "조회할 채팅방을 선택해주세요.")
    private long chat_room_id;
    @Nullable
    private long cursor;
}
