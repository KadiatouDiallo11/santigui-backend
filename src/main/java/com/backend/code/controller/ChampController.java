package com.backend.code.controller;

import com.backend.code.dtos.ChampRequestDTO;
import com.backend.code.dtos.ChampResponseDTO;
import com.backend.code.services.ChampService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champs")
@Tag(name = "Champ", description = "API de gestion des champs agricoles")
public class ChampController {

    private final ChampService champService;

    public ChampController(ChampService champService) {
        this.champService = champService;
    }

    @Operation(summary = "Créer un champ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChampResponseDTO create(@RequestBody ChampRequestDTO dto) {

        return champService.create(dto);
    }

    @Operation(summary = "Afficher tous les champs")
    @GetMapping
    public List<ChampResponseDTO> getAll() {

        return champService.getAll();
    }

    @Operation(summary = "Afficher un champ par ID")
    @GetMapping("/{id}")
    public ChampResponseDTO getById(@PathVariable Long id) {

        return champService.getById(id);
    }

    @Operation(summary = "Modifier un champ")
    @PutMapping("/{id}")
    public ChampResponseDTO update(@PathVariable Long id,
                                   @RequestBody ChampRequestDTO dto) {

        return champService.update(id, dto);
    }

    @Operation(summary = "Supprimer un champ")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        champService.delete(id);
    }
}