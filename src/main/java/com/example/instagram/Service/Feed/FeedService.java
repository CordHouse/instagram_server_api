package com.example.instagram.Service.Feed;

import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Entity.Feed.Feed;
import com.example.instagram.Repository.Feed.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    // 피드 조회 ( 수정 필요? )
    @Transactional
    public FeedResponseDto getFeed(long id) {
        Feed userFeed = feedRepository.findByUser_Id(id).orElseThrow();
        return new FeedResponseDto().toDo(userFeed);
    }
}
