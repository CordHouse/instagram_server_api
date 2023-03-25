package com.example.instagram.Dto.Follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnFollowRequestDto {
    @NotNull(message = "팔로우 취소할 사람을 선택해주세요.")
    private long user_id;
}
