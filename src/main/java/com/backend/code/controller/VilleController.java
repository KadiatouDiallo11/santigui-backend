package com.backend.code.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.code.dtos.VilleDTO;
import com.backend.code.services.VilleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/villes")
@Tag(name = "Ville", description = "CRUD des villes")
public class VilleController {

    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @Operation(summary = "Créer une ville")
    @ApiResponse(responseCode = "200", description = "Ville créée avec succès")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public VilleDTO create(@RequestBody VilleDTO dto) {
        return villeService.create(dto);
    }

    @Operation(summary = "Lister toutes les villes")
    @ApiResponse(responseCode = "200", description = "Liste des villes récupérée")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public List<VilleDTO> getAll() {
        return villeService.getAll();
    }

    @Operation(summary = "Récupérer une ville par ID")
    @ApiResponse(responseCode = "200", description = "Ville trouvée")
    @ApiResponse(responseCode = "404", description = "Ville introuvable")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public VilleDTO getById(@PathVariable String id) {
        return villeService.getById(id);
    }

    @Operation(summary = "Modifier une ville")
    @ApiResponse(responseCode = "200", description = "Ville mise à jour")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public VilleDTO update(@PathVariable String id,
                           @RequestBody VilleDTO dto) {
        return villeService.update(id, dto);
    }

    @Operation(summary = "Supprimer une ville")
    @ApiResponse(responseCode = "200", description = "Ville supprimée")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        villeService.delete(id);
    }
}