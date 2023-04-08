package com.example.instagram.Controller.DirectMessage;

import com.example.instagram.Dto.ChatRoom.ChatRoomRequestDto;
import com.example.instagram.Dto.DirectMessage.ChatRoomInfoResponseDto;
import com.example.instagram.Dto.DirectMessage.DirectMessageRequestDto;
import com.example.instagram.Dto.DirectMessage.DirectMessageResponseDto;
import com.example.instagram.Dto.DirectMessage.SendMessageRequestDto;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.DirectMessage.DirectMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DirectMessageController {
    private final DirectMessageService directMessageService;
    private final UserRepository userRepository;
    private static final int PAGE_SIZE = 2;

    // DM 전송
    @PostMapping("/direct/message")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody @Valid SendMessageRequestDto sendMessageRequestDto) {
        directMessageService.sendMessage(sendMessageRequestDto, getUser());
    }

    // DM 목록 조회
    @GetMapping("/direct/message")
    @ResponseStatus(HttpStatus.OK)
    public ChatRoomInfoResponseDto getDirectMessages(@RequestBody @Valid ChatRoomRequestDto chatRoomRequestDto,
                                                     @PageableDefault(size = PAGE_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return directMessageService.getDirectMessages(pageable, getUser(), chatRoomRequestDto);
    }

    // DM 상세 내역 조회
    @GetMapping("/direct/message/info")
    @ResponseStatus(HttpStatus.OK)
    public DirectMessageResponseDto getDirectMessageInfo(@RequestBody @Valid DirectMessageRequestDto directMessageRequestDto,
                                                         @PageableDefault(size = PAGE_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return directMessageService.getDirectMessageInfo(pageable, directMessageRequestDto, getUser());
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
