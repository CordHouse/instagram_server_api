package com.example.instagram.Dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsEditRequestDto {
    @NotNull(message = "수정할 게시글을 선택해주세요.")
    private long id;
    @NotBlank(message = "이미지를 업로드 해주세요.")
    private String image;
    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String content;
}
