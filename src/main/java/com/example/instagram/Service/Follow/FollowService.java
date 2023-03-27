package com.example.instagram.Service.Follow;

import com.example.instagram.Entity.Follow.Follow;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Follow.AlreadyFollowException;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.Follow.FollowRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 팔로우 신청
    @Transactional
    public void follow(long id, User user) {
        User followRequestUser = userRepository.findById(user.getId()).orElseThrow(NotFoundUserException::new);
        User followResponseUser = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        Follow newFollow = followRepository.findByHostAndUser(user.getId(), followResponseUser).orElseThrow(AlreadyFollowException::new);
        followRequestUser.setFollow(followRequestUser.getFollow()+1);
        followResponseUser.setFollowing(followResponseUser.getFollowing()+1);
        followRepository.save(newFollow);
    }

    // 팔로우 취소
    @Transactional
    public void unFollow(long id, User user) {
        User followRequestUser = userRepository.findById(user.getId()).orElseThrow(NotFoundUserException::new);
        User followResponseUser = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        followRequestUser.setFollow(followRequestUser.getFollow()-1);
        followResponseUser.setFollowing(followResponseUser.getFollowing()-1);
        followRepository.deleteById(id);
    }
}
