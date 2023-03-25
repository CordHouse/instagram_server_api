package com.example.instagram.Dto.User;

import com.example.instagram.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String nickname;
    private String profile_image_url;
    private long follow;
    private long following;

    public UserResponseDto toDo(User user) {
        return new UserResponseDto(
                user.getNickname(),
                user.getProfile_image_url(),
                user.getFollow(),
                user.getFollowing());
    }
}
