package com.example.instagram.Dto.User;

import com.example.instagram.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDto {
    private long id;
    private String nickname;
    private String profile_image_url;

    public UserRegisterResponseDto toDo(User user) {
        return new UserRegisterResponseDto(user.getId(), user.getNickname(), user.getProfile_image_url());
    }
}
