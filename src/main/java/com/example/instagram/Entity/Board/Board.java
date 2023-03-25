package com.example.instagram.Entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String content;

    public Board(String image, String content) {
        this.image = image;
        this.content = content;
    }
}
