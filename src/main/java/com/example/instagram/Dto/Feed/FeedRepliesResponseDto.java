package com.example.instagram.Dto.Feed;

import com.example.instagram.Entity.Replies.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRepliesResponseDto {
    private long id;
    private String content;
    private String nickname;
    private String profile_image_url;

    public FeedRepliesResponseDto toDo(Replies replies) {
        return new FeedRepliesResponseDto(
                replies.getId(),
                replies.getContent(),
                replies.getUser().getNickname(),
                replies.getUser().getProfile_image_url());
    }
}
