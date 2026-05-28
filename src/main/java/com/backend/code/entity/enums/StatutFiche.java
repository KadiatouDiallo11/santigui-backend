package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statut global d'une fiche", example = "VALIDEE")
public enum StatutFiche {
    ENVOYEE, 
    EN_ATTENTE, 
    VALIDEE
}