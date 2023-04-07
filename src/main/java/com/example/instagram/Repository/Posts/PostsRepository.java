package com.example.instagram.Repository.Posts;

import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {
    Optional<Posts> findByIdAndUser(long id, User user);
    void deleteByIdAndUser(long id, User user);
}
