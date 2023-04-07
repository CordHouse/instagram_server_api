package com.example.instagram.Repository.Posts;

import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.Posts.QPosts;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Posts> findByQUserPostsCustomCursorPaging(Pageable pageable, long id, long cursorId) {
        QPosts post = QPosts.posts;

        List<Posts> posts = jpaQueryFactory
                .select(post)
                .from(post)
                .leftJoin(post.user)
                .leftJoin(post.comments)
                .fetchJoin()
                .where(post.user.id.eq(id).and(cursorId(cursorId, post)))
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(posts);
    }

    public BooleanExpression cursorId(Long cursorId, QPosts post) {
        if(cursorId == null) {
            return null;
        }
        // post.id.gt -> where posts0_.id > ? 으로 출력 그 다음 자료들이 나와야 하는 경우 ( 게시글에 적합 )
        // post.id.lt -> where posts0_.id < ? 으로 출력 그 이전 자료들이 나와야 하는 경우 ( 채팅방에 적합 )
        return post.id.gt(cursorId);
    }
}
