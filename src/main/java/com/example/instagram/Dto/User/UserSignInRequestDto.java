package com.example.instagram.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRequestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String nickname;
}
