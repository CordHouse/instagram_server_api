package com.example.instagram.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {
    @NotNull(message = "프로필 조회할 유저 id를 입력해주세요.")
    private long id;
}
