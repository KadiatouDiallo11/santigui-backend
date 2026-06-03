
package com.backend.code.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.code.dtos.CommentaireRequestDTO;
import com.backend.code.dtos.CommentaireResponseDTO;
import com.backend.code.services.CommentaireService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/commentaires")
@Tag(
        name = "Commentaires",
        description = "Gestion des commentaires administrateur"
)
public class CommentaireController {

    private final CommentaireService commentaireService;

    public CommentaireController(
            CommentaireService commentaireService) {

        this.commentaireService = commentaireService;
    }

    @Operation(summary = "Créer un commentaire")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaireResponseDTO create(
            @RequestBody CommentaireRequestDTO dto) {

        return commentaireService.create(dto);
    }

    @Operation(summary = "Lister les commentaires")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public List<CommentaireResponseDTO> getAll() {

        return commentaireService.getAll();
    }

    @Operation(summary = "Obtenir un commentaire")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public CommentaireResponseDTO getById(
            @PathVariable String id) {

        return commentaireService.getById(id);
    }

    @Operation(summary = "Supprimer un commentaire")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id) {

        commentaireService.delete(id);
    }
    }