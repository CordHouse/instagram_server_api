package com.example.instagram.Repository.DirectMessage;

import com.example.instagram.Entity.DirectMessage.DirectMessage;
import com.example.instagram.Entity.DirectMessage.QDirectMessage;
import com.example.instagram.Entity.User.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class DirectMessageRepositoryImpl implements DirectMessageRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<DirectMessage> findQChatRoomAndQSenderOrQReceiver(Pageable pageable, long chatRoomId, User user, long cursorId) {
        QDirectMessage directMessage = QDirectMessage.directMessage;
        List<DirectMessage> directMessages = jpaQueryFactory
                .select(directMessage)
                .from(directMessage)
                .leftJoin(directMessage.chatRoom)
                .leftJoin(directMessage.sender)
                .leftJoin(directMessage.receiver)
                .fetchJoin()
                .where(directMessage.chatRoom.id.eq(chatRoomId).and(directMessage.sender.eq(user).or(directMessage.receiver.eq(user))
                        .and(cursorId(cursorId, directMessage))))
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(directMessages);
    }

    public BooleanExpression cursorId(Long cursorId, QDirectMessage directMessage) {
        if(cursorId == null) {
            return null;
        }
        return directMessage.id.loe(cursorId);
    }
}
