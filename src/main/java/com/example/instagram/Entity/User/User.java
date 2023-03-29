package com.example.instagram.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profile_image_url;

    @Column(nullable = false)
    private long follow;

    @Column(nullable = false)
    private long following;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleType role;

    public User(String nickname, String profile_image_url) {
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.follow = 0;
        this.following = 0;
    }
}
