package com.example.instagram.Entity.ChatRoom;

import com.example.instagram.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String last_message;

    @Column(nullable = false)
    private LocalDateTime last_sent_at;

    @Column(nullable = false)
    private long host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ChatRoom(User user, long host, String last_message, LocalDateTime last_sent_at) {
        this.user = user;
        this.host = host;
        this.last_message = last_message;
        this.last_sent_at = last_sent_at;
    }
}
