package com.example.instagram.Service.DirectMessage;

import com.example.instagram.Dto.ChatRoom.ChatRoomResponseDto;
import com.example.instagram.Dto.DirectMessage.DirectMessageInfoResponseDto;
import com.example.instagram.Dto.DirectMessage.SendMessageRequestDto;
import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.DirectMessage.DirectMessage;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.ChatRoom.NotFoundChatRoomException;
import com.example.instagram.Exception.DirectMessage.NotFoundDirectMessageException;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.ChatRoom.ChatRoomRepository;
import com.example.instagram.Repository.DirectMessage.DirectMessageRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectMessageService {
    private final DirectMessageRepository directMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // DM 전송
    @Transactional
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto, User user) {
        // 1. 대상 유저가 있는지 db에서 찾아본다.
        User targetUser = userRepository.findById(sendMessageRequestDto.getUser_id()).orElseThrow(NotFoundUserException::new);
        // 2. 있다면, DM을 전송해야 하는데 이전에 대화하던 방이 있는지 찾아본다.
        ChatRoom searchChatRoom = chatRoomRepository.findByHostAndUserOrUserAndHost(user.getId(), targetUser, targetUser, user.getId());
        LocalDateTime nowDate = LocalDateTime.now();
        // 3. 방이 있다면, 대화를 이어나간다.
        if(searchChatRoom != null) {
            searchChatRoom.setLast_message(sendMessageRequestDto.getContent());
            searchChatRoom.setLast_sent_at(nowDate);
            DirectMessage newDirectMessage = new DirectMessage(searchChatRoom, targetUser, sendMessageRequestDto.getContent(), user.getId(), nowDate);
            chatRoomRepository.save(searchChatRoom);
            directMessageRepository.save(newDirectMessage);
            return ;
        }
        // 3-1. 없다면 새로 방을 만들고 첫 DM을 보낸다.
        ChatRoom newChatRoom = new ChatRoom(targetUser, user.getId(), sendMessageRequestDto.getContent(), nowDate);
        DirectMessage newDirectMessage = new DirectMessage(newChatRoom, targetUser, sendMessageRequestDto.getContent(), user.getId(), nowDate);
        chatRoomRepository.save(newChatRoom);
        directMessageRepository.save(newDirectMessage);
    }

    // DM 목록 조회
    @Transactional
    public List<ChatRoomResponseDto> getDirectMessages(User user) {
        List<ChatRoom> targetUserChatRoom = chatRoomRepository.findAllByUserOrTarget(user, user.getId());

        if(targetUserChatRoom.isEmpty()) {
            throw new NotFoundChatRoomException();
        }

        List<ChatRoomResponseDto> chatRoomResponseDtoList = new LinkedList<>();
        targetUserChatRoom.stream().map(dto -> chatRoomResponseDtoList.add(new ChatRoomResponseDto().toDo(dto)))
                .collect(Collectors.toList());
        return chatRoomResponseDtoList;
    }

    // DM 상세 내역 조회
    @Transactional
    public List<DirectMessageInfoResponseDto> getDirectMessageInfo(User user) {
        List<DirectMessage> directMessageInfo = directMessageRepository.findAllBySenderOrUser(user, user);

        if(directMessageInfo.isEmpty()) {
            throw new NotFoundDirectMessageException();
        }

        List<DirectMessageInfoResponseDto> directMessageInfoResponseDtoList = new LinkedList<>();
        directMessageInfo.stream().map(dto -> directMessageInfoResponseDtoList.add(new DirectMessageInfoResponseDto().toDo(dto)))
                .collect(Collectors.toList());
        return directMessageInfoResponseDtoList;
    }
}
