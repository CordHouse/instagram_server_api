package com.example.instagram.Repository.Follow;

import com.example.instagram.Entity.Follow.Follow;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findBySenderAndReceiver(User send, User receiver);
}
