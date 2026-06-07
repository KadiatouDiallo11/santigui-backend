package com.backend.code.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.code.dtos.AdministrateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;
import com.backend.code.services.UtilisateurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/administrateurs")
@Tag(name = "Administrateur", description = "CRUD dedie aux administrateurs")
public class AdministrateurController {

    private final UtilisateurService service;

    public AdministrateurController(UtilisateurService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un administrateur")
    @ApiResponse(responseCode = "200", description = "Administrateur créé avec succès")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO create(@RequestBody AdministrateurRequestDTO dto) {
        return service.createAdministrateur(dto);
    }

    @Operation(summary = "Lister tous les administrateurs")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UtilisateurResponseDTO> getAll() {
        return service.findAllAdministrateurs();
    }

    @Operation(summary = "Récupérer un administrateur par ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO getById(@PathVariable String id) {
        return service.findAdministrateurById(id);
    }

    @Operation(summary = "Modifier un administrateur")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO update(@PathVariable String id, @RequestBody AdministrateurRequestDTO dto) {
        return service.updateAdministrateur(id, dto);
    }

    @Operation(summary = "Supprimer un administrateur")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        service.deleteAdministrateur(id);
    }

    @Operation(summary = "Activer un compte administrateur")
    @PatchMapping("/{id}/activer")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO activer(@PathVariable String id) {
        return service.activerAdministrateur(id);
    }

    @Operation(summary = "Désactiver un compte administrateur")
    @PatchMapping("/{id}/desactiver")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO desactiver(@PathVariable String id) {
        return service.desactiverAdministrateur(id);
    }
}