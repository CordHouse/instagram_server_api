package com.example.instagram.Repository.ChatRoom;

import com.example.instagram.Entity.ChatRoom.ChatRoom;
import com.example.instagram.Entity.ChatRoom.QChatRoom;
import com.example.instagram.Entity.User.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ChatRoom> findByQChatRoomQHostOrQTarget(Pageable pageable, User host, User target, long cursorId) {
        QChatRoom chatRoom = QChatRoom.chatRoom;

        List<ChatRoom> chatRooms = jpaQueryFactory
                .select(chatRoom)
                .from(chatRoom)
                .leftJoin(chatRoom.host)
                .leftJoin(chatRoom.target)
                .fetchJoin()
                .where(chatRoom.host.eq(host).or(chatRoom.target.eq(target)).and(cursorId(cursorId, chatRoom)))
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(chatRooms);
    }

    public BooleanExpression cursorId(Long cursorId, QChatRoom chatRoom) {
        if(cursorId == null) {
            return null;
        }
        // post.id.gt -> where posts0_.id > ? 으로 출력 그 다음 자료들이 나와야 하는 경우 ( 게시글에 적합 )
        // post.id.lt -> where posts0_.id < ? 으로 출력 그 이전 자료들이 나와야 하는 경우 ( 채팅방에 적합 )
        System.out.println(chatRoom.id.gt(cursorId));
        return chatRoom.id.gt(cursorId);
    }
}
