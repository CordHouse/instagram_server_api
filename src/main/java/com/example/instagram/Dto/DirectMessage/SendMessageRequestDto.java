package com.example.instagram.Dto.DirectMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageRequestDto {
    @NotNull(message = "메세지를 보낼 상대방 아이디를 입력하세요.")
    private long user_id;
    @NotBlank(message = "보낼 메세지 내용을 입력하세요.")
    private String content;
}
