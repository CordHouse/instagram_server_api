package com.example.instagram.Dto.Token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenReissueRequestDto {
    @NotBlank(message = "리프레시 토큰을 입력해주세요.")
    private String refresh_token;
}
