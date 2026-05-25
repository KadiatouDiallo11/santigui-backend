package com.backend.code.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;
import com.backend.code.services.UtilisateurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateur", description = "CRUD des utilisateurs ADMIN / EXPLOITANT")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès")
    @PostMapping
    public UtilisateurResponseDTO create(@RequestBody UtilisateurRequestDTO dto) {
        return service.createUser(dto);
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public List<UtilisateurResponseDTO> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Récupérer utilisateur par ID")
    @GetMapping("/{id}")
    public UtilisateurResponseDTO getById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    
    @Operation(summary = "Modifier un utilisateur")
    @PutMapping("/{id}")
    public UtilisateurResponseDTO update(@PathVariable String id,
                                         @RequestBody UtilisateurRequestDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Activer un compte utilisateur")
    @PatchMapping("/{id}/activer")
    public UtilisateurResponseDTO activer(@PathVariable String id) {
        return service.activerCompte(id);
    }

    @Operation(summary = "Désactiver un compte utilisateur")
    @PatchMapping("/{id}/desactiver")
    public UtilisateurResponseDTO desactiver(@PathVariable String id) {
        return service.desactiverCompte(id);
    }
}