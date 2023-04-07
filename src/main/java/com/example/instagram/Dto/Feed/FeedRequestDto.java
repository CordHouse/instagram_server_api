package com.example.instagram.Dto.Feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {
    @NotNull(message = "피드 조회를 원하는 유저의 id 값을 입력해주세요.")
    private long user_id;
    @Nullable
    private long cursor;
}
