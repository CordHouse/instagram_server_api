package com.example.instagram.Dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditRequestDto {
    @NotNull(message = "수정할 댓글을 선택해주세요.")
    private long id;
    @NotBlank(message = "수정할 댓글을 입력해주세요.")
    private String content;
}
