package com.example.instagram.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    @NotBlank(message = "사용할 닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "사용할 프로필을 입력해주세요.")
    private String profile_image;
}
