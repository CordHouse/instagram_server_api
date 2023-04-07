package com.example.instagram.Dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDeleteRequestDto {
    @NotNull(message = "삭제할 게시글을 선택해주세요.")
    private long id;
}
