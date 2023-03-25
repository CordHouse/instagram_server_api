package com.example.instagram.Controller.DirectMessage;

import com.example.instagram.Dto.ChatRoom.ChatRoomResponseDto;
import com.example.instagram.Dto.DirectMessage.DirectMessageInfoResponseDto;
import com.example.instagram.Dto.DirectMessage.SendMessageRequestDto;
import com.example.instagram.Service.DirectMessage.DirectMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DirectMessageController {
    private final DirectMessageService directMessageService;

    // DM 전송
    @PostMapping("/direct/message")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody @Valid SendMessageRequestDto sendMessageRequestDto) {
        directMessageService.sendMessage(sendMessageRequestDto);
    }

    // DM 목록 조회
    @GetMapping("/direct/message/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatRoomResponseDto> getDirectMessages(@PathVariable long id) {
        return directMessageService.getDirectMessages(id);
    }

    // DM 상세 내역 조회
    @GetMapping("/direct/message/info/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectMessageInfoResponseDto> getDirectMessageInfo(@PathVariable long id) {
        return directMessageService.getDirectMessageInfo(id);
    }
}
