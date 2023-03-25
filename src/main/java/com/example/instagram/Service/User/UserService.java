package com.example.instagram.Service.User;

import com.example.instagram.Dto.User.*;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = new User(userRegisterRequestDto.getNickname(), userRegisterRequestDto.getProfile_image());
        userRepository.save(newUser);
        return new UserRegisterResponseDto().toDo(newUser);
    }

    // 회원탈퇴
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    // 프로필 조회
    @Transactional
    public UserResponseDto getProfile(long id) {
        User userProfile = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        return new UserResponseDto().toDo(userProfile);
    }

    // 프로필 수정
    @Transactional
    public UserEditResponseDto editProfile(UserEditRequestDto userEditRequestDto) {
        User userProfile = userRepository.findById(1L).orElseThrow(NotFoundUserException::new);
        userProfile.setNickname(userEditRequestDto.getNickname());
        userProfile.setProfile_image_url(userEditRequestDto.getProfile_image_url());
        return new UserEditResponseDto().toDo(userProfile);
    }
}
