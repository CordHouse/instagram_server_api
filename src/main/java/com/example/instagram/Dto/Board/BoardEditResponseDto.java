package com.example.instagram.Dto.Board;

import com.example.instagram.Entity.Board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditResponseDto {
    private long id;
    private String image_url;
    private String content;

    public BoardEditResponseDto toDo(Board board) {
        return new BoardEditResponseDto(board.getId(), board.getImage(), board.getContent());
    }
}
