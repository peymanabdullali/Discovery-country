package com.example.discovery_country.service.auth;

import com.example.discovery_country.dao.entity.auth.RefreshToken;
import com.example.discovery_country.dao.entity.auth.User;
import com.example.discovery_country.dao.repository.auth.RefreshTokenRepository;
import com.example.discovery_country.dao.repository.auth.UserRepository;
import com.example.discovery_country.exception.RefreshTokenExpiredException;
import com.example.discovery_country.exception.RefreshTokenNotFoundException;
import com.example.discovery_country.exception.UserNotFoundException;
import com.example.discovery_country.model.dto.response.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final RefreshTokenRepository refreshTokenRepository;



    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"User not found with email : " + username));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            long refreshTokenValidity = 2* 60 * 1000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();

            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFoundException(HttpStatus.NOT_FOUND.name(),"Refresh token not found!"));

        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refToken);
            throw new RefreshTokenExpiredException(HttpStatus.UNAUTHORIZED.name(),"Refresh Token expired");
        }

        return refToken;
    }

    public AuthResponse refreshToken(String refreshToken) {
        RefreshToken refToken = verifyRefreshToken(refreshToken);
        User user = refToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refToken.getRefreshToken())
                .build();
    }
}