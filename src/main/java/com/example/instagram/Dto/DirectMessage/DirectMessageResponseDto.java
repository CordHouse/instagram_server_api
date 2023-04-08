package com.example.instagram.Dto.DirectMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessageResponseDto {
    private List<DirectMessageInfoResponseDto> directMessageInfoResponseDto;

    public DirectMessageResponseDto toDo(List<DirectMessageInfoResponseDto> directMessageInfoResponseDto) {
        return new DirectMessageResponseDto(directMessageInfoResponseDto);
    }
}
