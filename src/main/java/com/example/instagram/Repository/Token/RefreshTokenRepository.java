package com.example.instagram.Repository.Token;

import com.example.instagram.Entity.Token.RefreshToken;
import com.example.instagram.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndUser(String refreshToken, User user);
}
