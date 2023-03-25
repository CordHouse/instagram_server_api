package com.example.instagram.Dto.Comment;

import com.example.instagram.Entity.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateResponseDto {
    private long id;
    private String content;

    public CommentCreateResponseDto toDo(Comment comment) {
        return new CommentCreateResponseDto(comment.getId(), comment.getContent());
    }
}
