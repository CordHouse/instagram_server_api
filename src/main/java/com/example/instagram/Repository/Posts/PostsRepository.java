package com.example.instagram.Repository.Posts;

import com.example.instagram.Entity.Posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByUser_Id(long id);
}
