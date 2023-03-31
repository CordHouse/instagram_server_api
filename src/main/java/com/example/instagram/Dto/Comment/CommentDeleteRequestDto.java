package com.example.instagram.Dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequestDto {
    @NotNull(message = "삭제할 댓글을 선택해주세요.")
    private long id;
}
