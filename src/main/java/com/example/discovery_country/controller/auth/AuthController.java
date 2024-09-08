package com.example.discovery_country.controller.auth;


import com.example.discovery_country.model.dto.request.auth.LoginRequest;
import com.example.discovery_country.model.dto.request.auth.RefreshTokenRequest;
import com.example.discovery_country.model.dto.request.auth.RegisterRequest;
import com.example.discovery_country.model.dto.response.auth.AuthResponse;
import com.example.discovery_country.service.auth.AuthService;
import com.example.discovery_country.service.auth.RefreshTokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/verify-email/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        authService.verifyEmail(email);
        return ResponseEntity.ok("verified email,  otp code sent");
    }

    @PostMapping("/confirm-register/{otp}/{email}")
    public ResponseEntity<String> confirmRegistration(@PathVariable String email,
                                                      @PathVariable Integer otp) {
        authService.confirmRegistration(email, otp);
        return ResponseEntity.ok("Registration confirmed!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthResponse authResponse = refreshTokenService.refreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(authResponse);
    }

}