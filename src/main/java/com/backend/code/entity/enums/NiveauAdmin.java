package com.backend.code.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Niveau d'acces d'un administrateur", example = "SUPER_ADMIN")
public enum NiveauAdmin {
	SUPER_ADMIN, 
    SECRETAIRE
}
