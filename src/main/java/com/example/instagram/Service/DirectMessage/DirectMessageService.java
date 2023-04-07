package com.example.instagram.Service.DirectMessage;

import com.example.instagram.Dto.ChatRoom.ChatRoomRequestDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User receiverUser = userRepository.findById(sendMessageRequestDto.getUser_id()).orElseThrow(NotFoundUserException::new);
        ChatRoom searchChatRoom = chatRoomRepository.findByTargetAndHostOrHostAndTarget(user, receiverUser, receiverUser, user);
        LocalDateTime nowDate = LocalDateTime.now();

        if(searchChatRoom != null) {
            searchChatRoom.setLast_message(sendMessageRequestDto.getContent());
            searchChatRoom.setLast_sent_at(nowDate);
            DirectMessage newDirectMessage = createDirectMessage(searchChatRoom, user, receiverUser, sendMessageRequestDto.getContent(), nowDate);
            chatRoomRepository.save(searchChatRoom);
            directMessageRepository.save(newDirectMessage);
            return ;
        }
        ChatRoom newChatRoom = createChatRoom(user, receiverUser, sendMessageRequestDto.getContent(), nowDate);
        DirectMessage newDirectMessage = createDirectMessage(newChatRoom, user, receiverUser, sendMessageRequestDto.getContent(), nowDate);
        chatRoomRepository.save(newChatRoom);
        directMessageRepository.save(newDirectMessage);
    }

    // DM 목록 조회
    @Transactional
    public List<ChatRoomResponseDto> getDirectMessages(Pageable pageable, User user, ChatRoomRequestDto chatRoomRequestDto) {
        Page<ChatRoom> targetUserChatRoom = chatRoomRepository.findByQChatRoomQHostOrQTarget(pageable, user, user, chatRoomRequestDto.getCursor());

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
        List<DirectMessage> directMessageInfo = directMessageRepository.findAllBySenderOrReceiver(user, user);

        if(directMessageInfo.isEmpty()) {
            throw new NotFoundDirectMessageException();
        }

        List<DirectMessageInfoResponseDto> directMessageInfoResponseDtoList = new LinkedList<>();
        directMessageInfo.stream().map(dto -> directMessageInfoResponseDtoList.add(new DirectMessageInfoResponseDto().toDo(dto)))
                .collect(Collectors.toList());
        return directMessageInfoResponseDtoList;
    }

    /***
     * 채팅방 생성
     * @param host 송신자
     * @param target 수신자
     * @param message 메시지
     * @param nowDate 보낸 시간
     * @return 채팅방 객체
     */
    public ChatRoom createChatRoom(User host, User target, String message, LocalDateTime nowDate) {
        return ChatRoom.builder()
                .host(host)
                .target(target)
                .last_message(message)
                .last_sent_at(nowDate)
                .build();
    }

    /***
     * 메신저 전송
     * @param chatRoom 채팅방 객체
     * @param sender 송신자
     * @param receiver 수신자
     * @param message 메시지
     * @param nowDate 시간
     * @return 메신저 객체
     */
    public DirectMessage createDirectMessage(ChatRoom chatRoom, User sender, User receiver, String message, LocalDateTime nowDate) {
        return DirectMessage.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .receiver(receiver)
                .message(message)
                .sent_at(nowDate)
                .build();
    }
}
