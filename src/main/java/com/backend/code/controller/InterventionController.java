package com.backend.code.controller;

import com.backend.code.dtos.InterventionRequestDTO;
import com.backend.code.dtos.InterventionResponseDTO;
import com.backend.code.services.InterventionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventions")
@Tag(name = "Intervention",
     description = "API de gestion des fiches d'intervention")
public class InterventionController {

    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @Operation(summary = "Créer une fiche d'intervention")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterventionResponseDTO create(
            @RequestBody InterventionRequestDTO dto) {

        return interventionService.create(dto);
    }

    @Operation(summary = "Lister toutes les interventions")
    @GetMapping
    public List<InterventionResponseDTO> getAll() {

        return interventionService.getAll();
    }

    @Operation(summary = "Obtenir une intervention par ID")
    @GetMapping("/{id}")
    public InterventionResponseDTO getById(
            @PathVariable Long id) {

        return interventionService.getById(id);
    }

    @Operation(summary = "Modifier une intervention")
    @PutMapping("/{id}")
    public InterventionResponseDTO update(
            @PathVariable Long id,
            @RequestBody InterventionRequestDTO dto) {

        return interventionService.update(id, dto);
    }

    @Operation(summary = "Supprimer une intervention")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        interventionService.delete(id);
    }
}