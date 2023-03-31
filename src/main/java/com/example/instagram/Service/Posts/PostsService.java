package com.example.instagram.Service.Posts;

import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Dto.Posts.PostsCreateRequestDto;
import com.example.instagram.Dto.Posts.PostsCreateResponseDto;
import com.example.instagram.Dto.Posts.PostsEditRequestDto;
import com.example.instagram.Dto.Posts.PostsEditResponseDto;
import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Posts.NotFoundFeedException;
import com.example.instagram.Exception.Posts.NotFoundPostsException;
import com.example.instagram.Repository.Posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    // 글 생성
    @Transactional
    public PostsCreateResponseDto createBoard(PostsCreateRequestDto postsCreateRequestDto, User user) {
        Posts newPosts = Posts.builder()
                .user(user)
                .image(postsCreateRequestDto.getImage().getOriginalFilename())
                .content(postsCreateRequestDto.getContent())
                .build();
        postsRepository.save(newPosts);

        return new PostsCreateResponseDto().toDo(newPosts);
    }

    // 글 수정
    @Transactional
    public PostsEditResponseDto editBoard(PostsEditRequestDto postsEditRequestDto, User user) {
        Posts editPosts = postsRepository.findByIdAndUser(postsEditRequestDto.getId(), user).orElseThrow(NotFoundPostsException::new);
        editPosts.setImage(postsEditRequestDto.getImage().getOriginalFilename());
        editPosts.setContent(postsEditRequestDto.getContent());
        return new PostsEditResponseDto().toDo(editPosts);
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id, User user) {
        postsRepository.deleteByIdAndUser(id, user);
    }

    // 피드 조회
    @Transactional
    public FeedResponseDto getFeed(User user) {
        List<Posts> feedList = postsRepository.findAllByUser_Id(user.getId());
        if(feedList.isEmpty()) {
            throw new NotFoundFeedException();
        }
        return new FeedResponseDto().toDo(feedList);
    }
}
