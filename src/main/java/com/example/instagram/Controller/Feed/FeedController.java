package com.example.instagram.Controller.Feed;

import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Service.Feed.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedController {
    private final FeedService feedService;

    // 피드 조회
    @GetMapping("/feed/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedResponseDto getFeed(@PathVariable long id) {
        return feedService.getFeed(id);
    }
}
