package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type fonctionnel d'une notification", example = "FICHE_SOUMISE")
public enum TypeNotification {

    FICHE_SOUMISE,
    COMMENTAIRE_RECU
}