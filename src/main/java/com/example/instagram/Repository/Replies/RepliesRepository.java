package com.example.instagram.Repository.Replies;

import com.example.instagram.Entity.Replies.Replies;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepliesRepository extends JpaRepository<Replies, Long> {
    Optional<Replies> findByIdAndUser(long id, User user);
    void deleteByIdAndUser(long id, User user);
}
