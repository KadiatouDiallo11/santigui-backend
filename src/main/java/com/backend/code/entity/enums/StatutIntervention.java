package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statut du cycle de vie d'une intervention", example = "SOUMISE")
public enum StatutIntervention {

    BROUILLON,
    SOUMISE,
    COMMENTEE
}