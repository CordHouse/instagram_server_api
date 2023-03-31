package com.example.instagram.Dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsCreateRequestDto {
    private MultipartFile image;

    @NotBlank(message = "게시물 내용을 입력해주세요.")
    private String content;
}
