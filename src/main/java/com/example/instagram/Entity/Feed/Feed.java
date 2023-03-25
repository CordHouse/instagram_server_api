package com.example.instagram.Entity.Feed;

import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Entity.Replies.*;
import com.example.instagram.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replies_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Replies replies;

    public Feed(User user, Posts posts, Comment comment, Replies replies) {
        this.user = user;
        this.posts = posts;
        this.comment = comment;
        this.replies = replies;
    }
}
