package com.backend.code.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.code.dtos.NotificationResponseDTO;
import com.backend.code.services.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "Consultation et gestion des notifications des administrateurs et exploitants")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(
            summary = "Lister les notifications d'un utilisateur",
            description = "Retourne toutes les notifications d'un administrateur ou d'un exploitant a partir de son identifiant, des plus recentes aux plus anciennes."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications recuperees avec succes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = NotificationResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable", content = @Content)
    })
    @GetMapping("/utilisateurs/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public List<NotificationResponseDTO> getByUtilisateurId(
            @Parameter(description = "Identifiant de l'utilisateur cible", example = "user-123")
            @PathVariable String userId) {

        return notificationService.getByUtilisateurId(userId);
    }

    @Operation(
            summary = "Marquer une notification comme lue",
            description = "Passe le statut d'une notification a l'etat lu et renseigne la date de lecture. Si elle etait deja lue, elle est retournee telle quelle."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification mise a jour", content = @Content(schema = @Schema(implementation = NotificationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Notification introuvable", content = @Content)
    })
    @PatchMapping("/{id}/marquer-lue")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public NotificationResponseDTO markAsRead(
            @Parameter(description = "Identifiant de la notification a marquer comme lue", example = "notif-123")
            @PathVariable String id) {

        return notificationService.markAsRead(id);
    }

    @Operation(
            summary = "Marquer toutes les notifications d'un utilisateur comme lues",
            description = "Met a jour toutes les notifications non lues d'un utilisateur en une seule operation."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications mises a jour", content = @Content(array = @ArraySchema(schema = @Schema(implementation = NotificationResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable", content = @Content)
    })
    @PatchMapping("/utilisateurs/{userId}/marquer-tout-lu")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public List<NotificationResponseDTO> markAllAsRead(
            @Parameter(description = "Identifiant de l'utilisateur cible", example = "user-123")
            @PathVariable String userId) {

        return notificationService.markAllAsReadByUtilisateurId(userId);
    }

    @Operation(
            summary = "Supprimer une notification",
            description = "Supprime definitivement une notification a partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notification supprimee"),
            @ApiResponse(responseCode = "404", description = "Notification introuvable", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant de la notification a supprimer", example = "notif-123")
            @PathVariable String id) {

        notificationService.delete(id);
    }

    @Operation(
            summary = "Supprimer toutes les notifications d'un utilisateur",
            description = "Efface l'historique des notifications d'un administrateur ou d'un exploitant a partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notifications supprimees"),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable", content = @Content)
    })
    @DeleteMapping("/utilisateurs/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllByUtilisateurId(
            @Parameter(description = "Identifiant de l'utilisateur cible", example = "user-123")
            @PathVariable String userId) {

        notificationService.deleteAllByUtilisateurId(userId);
    }
}