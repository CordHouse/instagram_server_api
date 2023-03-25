package com.example.instagram.Dto.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditRequestDto {
    @NotBlank(message = "이미지를 업로드 해주세요.")
    private String image;
    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String content;
}