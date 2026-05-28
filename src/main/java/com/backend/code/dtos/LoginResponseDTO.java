package com.backend.code.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reponse retournee apres une authentification reussie")
public class LoginResponseDTO {
    @Schema(description = "Jeton JWT a utiliser pour les appels authentifies", example = "eyJhbGciOiJIUzI1NiJ9...")
    public String token;

    @Schema(description = "Type de compte connecte. Valeurs possibles: ADMIN, EXPLOITANT", example = "EXPLOITANT", allowableValues = {"ADMIN", "EXPLOITANT"})
    public String type;

    @Schema(description = "Adresse email du compte connecte", example = "explo@gmail.com")
    public String email;

    @Schema(description = "Informations du compte connecte")
    public UtilisateurResponseDTO user;
}