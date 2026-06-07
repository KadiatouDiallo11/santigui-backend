package com.backend.code.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.code.dtos.LoginRequestDTO;
import com.backend.code.dtos.LoginResponseDTO;
import com.backend.code.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur")
    @SecurityRequirements
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/logout")
    @Operation(summary = "Déconnexion utilisateur")
    @PreAuthorize("isAuthenticated()")
    public Map<String, String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        return service.logout(authorizationHeader);
    }
}