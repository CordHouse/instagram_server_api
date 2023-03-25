package com.example.instagram.Dto.DirectMessage;

import com.example.instagram.Entity.DirectMessage.DirectMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessageInfoResponseDto {
    private long id;
    private String nickname;
    private String profile_image_url;
    private String content;
    private LocalDateTime sent_at;

    public DirectMessageInfoResponseDto toDo(DirectMessage directMessage) {
        return new DirectMessageInfoResponseDto(
                directMessage.getId(),
                directMessage.getUser().getNickname(),
                directMessage.getUser().getProfile_image_url(),
                directMessage.getMessage(),
                directMessage.getSent_at());
    }
}
