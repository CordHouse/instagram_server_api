package com.example.instagram.Dto.Replies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesCreateRequestDto {
    @NotNull(message = "답글을 입력할 댓글을 선택해주세요.")
    private long comment_id;
    @NotBlank(message = "답글의 내용을 입력해주세요.")
    private String content;
}
