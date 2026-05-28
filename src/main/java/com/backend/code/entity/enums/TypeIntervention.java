package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type d'intervention realisee sur un champ", example = "TRAITEMENT")
public enum TypeIntervention {

    LABOUR,
    SEMIS,
    TRAITEMENT,
    IRRIGATION,
    RECOLTE,
    AUTRE
}