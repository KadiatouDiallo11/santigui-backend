package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type de commentaire associe a une fiche", example = "COMMENTAIRE")
public enum TypeCommentaire {
    COMMENTAIRE, 
    RECOMMANDATION
}
