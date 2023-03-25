package com.example.instagram.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditRequestDto {
    @NotBlank(message = "수정할 유저 닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "수정할 프로필 사진을 넣어주세요.")
    private String profile_image_url;
}
