package com.example.instagram.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profile_image_url;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long follow;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long following;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleType role;
}
