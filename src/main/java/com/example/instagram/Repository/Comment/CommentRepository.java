package com.example.instagram.Repository.Comment;

import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(long id, User user);
    void deleteByIdAndUser(long id, User user);
}
