package com.example.instagram.Dto.Board;

import com.example.instagram.Entity.Board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateResponseDto {
    private long id;
    private String image;
    private String content;

    public BoardCreateResponseDto toDo(Board board) {
        return new BoardCreateResponseDto(board.getId(), board.getImage(), board.getContent());
    }
}
