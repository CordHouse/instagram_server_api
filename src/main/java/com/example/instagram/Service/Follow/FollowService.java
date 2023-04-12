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

    private static final int COUNT = 1;

    // 팔로우 신청
    @Transactional
    public void follow(long id, User user) {
        User followResponseUser = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        followConfirm(user, followResponseUser);
        user.setFollow(user.getFollow()+COUNT);
        followResponseUser.setFollowing(followResponseUser.getFollowing()+COUNT);
    }

    // 팔로우 취소
    @Transactional
    public void unFollow(long id, User user) {
        User followResponseUser = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        user.setFollow(user.getFollow()-COUNT);
        followResponseUser.setFollowing(followResponseUser.getFollowing()-COUNT);
        followRepository.deleteByReceiver(followResponseUser);
    }

    /***
     * 팔로우가 가능한지 확인한다. ( 이미 신청한 사용자라면 예외처리 )
     * @param user 팔로우 신청자
     * @param followResponseUser 팔로우 신청을 받는 사람
     */
    @Transactional
    public void followConfirm(User user, User followResponseUser) {
        Follow newFollow = followRepository.findBySenderAndReceiver(user, followResponseUser);
        if(newFollow == null) {
            newFollow = Follow.builder()
                    .sender(user)
                    .receiver(followResponseUser)
                    .build();
            followRepository.save(newFollow);
            return;
        }
        throw new AlreadyFollowException();
    }
}
