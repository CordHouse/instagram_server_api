package com.example.instagram.Dto.Replies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesDeleteRequestDto {
    @NotNull(message = "삭제할 답글을 선택해주세요.")
    private long id;
}
