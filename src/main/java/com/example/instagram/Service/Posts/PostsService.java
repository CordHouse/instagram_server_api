package com.example.instagram.Service.Posts;

import com.example.instagram.Dto.Posts.PostsCreateRequestDto;
import com.example.instagram.Dto.Posts.PostsCreateResponseDto;
import com.example.instagram.Dto.Posts.PostsEditRequestDto;
import com.example.instagram.Dto.Posts.PostsEditResponseDto;
import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Posts.NotFoundPostsException;
import com.example.instagram.Repository.Posts.PostsRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    // 글 생성
    @Transactional
    public PostsCreateResponseDto createBoard(PostsCreateRequestDto postsCreateRequestDto) {
        User user = userRepository.findById(1L).orElseThrow();
        Posts newPosts = new Posts(user, postsCreateRequestDto.getImage(), postsCreateRequestDto.getContent());
        postsRepository.save(newPosts);
        return new PostsCreateResponseDto().toDo(newPosts);
    }

    // 글 수정
    @Transactional
    public PostsEditResponseDto editBoard(long id, PostsEditRequestDto postsEditRequestDto) {
        Posts editPosts = postsRepository.findById(id).orElseThrow(NotFoundPostsException::new);
        editPosts.setImage(postsEditRequestDto.getImage());
        editPosts.setContent(postsEditRequestDto.getContent());
        return new PostsEditResponseDto().toDo(editPosts);
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id) {
        postsRepository.deleteById(id);
    }
}
