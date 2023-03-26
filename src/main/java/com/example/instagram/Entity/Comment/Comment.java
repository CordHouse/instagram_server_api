package com.example.instagram.Entity.Comment;

import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.Replies.Replies;
import com.example.instagram.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<Replies> replies;

    public Comment(Posts posts, String content, User user) {
        this.posts = posts;
        this.content = content;
        this.user = user;
    }
}
