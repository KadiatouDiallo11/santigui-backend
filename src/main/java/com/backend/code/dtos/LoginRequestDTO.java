package com.backend.code.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Identifiants de connexion")
public class LoginRequestDTO {

    @Schema(description = "Adresse email de l'utilisateur", example = "explo@gmail.com")
    public String email;

    @Schema(description = "Mot de passe du compte", example = "MotDePasse123")
    public String password;
}
    