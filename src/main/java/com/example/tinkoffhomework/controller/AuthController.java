package com.example.tinkoffhomework.controller;

import com.example.tinkoffhomework.dto.JwtAuthenticationResponse;
import com.example.tinkoffhomework.dto.user.UserSingInDto;
import com.example.tinkoffhomework.dto.user.UserSingUpDto;
import com.example.tinkoffhomework.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/reg")
    public JwtAuthenticationResponse signUp(@RequestBody UserSingUpDto request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody UserSingInDto request) {
        return authenticationService.signIn(request);
    }
}
