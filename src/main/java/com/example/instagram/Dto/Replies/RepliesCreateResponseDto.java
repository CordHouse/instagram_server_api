package com.example.instagram.Dto.Replies;

import com.example.instagram.Entity.Replies.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesCreateResponseDto {
    private long id;
    private String content;

    public RepliesCreateResponseDto toDo(Replies replies) {
        return new RepliesCreateResponseDto(replies.getId(), replies.getContent());
    }
}
