package com.example.instagram.Dto.Replies;

import com.example.instagram.Entity.Replies.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesEditResponseDto {
    private long id;
    private String content;

    public RepliesEditResponseDto toDo(Replies replies) {
        return new RepliesEditResponseDto(replies.getId(), replies.getContent());
    }
}
