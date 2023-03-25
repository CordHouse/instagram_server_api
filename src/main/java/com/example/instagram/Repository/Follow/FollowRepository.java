package com.example.instagram.Repository.Follow;

import com.example.instagram.Entity.Follow.Follow;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByHostAndUser(long id, User user);
}
