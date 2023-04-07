package com.example.instagram.Repository.Posts;

import com.example.instagram.Entity.Posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsRepositoryCustom {
    Page<Posts> findByQUserPostsCustomCursorPaging(Pageable pageable, long id, long cursorId);
}
