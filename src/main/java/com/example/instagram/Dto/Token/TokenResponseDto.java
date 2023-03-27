package com.example.instagram.Dto.Token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String access_token;

    public TokenResponseDto toDo(String access_token) {
        return new TokenResponseDto(access_token);
    }
}
