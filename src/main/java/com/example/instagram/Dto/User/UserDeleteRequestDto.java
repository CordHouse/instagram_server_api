package com.example.instagram.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequestDto {
    @NotNull(message = "삭제할 계정을 선택해주세요.")
    private long user_id;
}
