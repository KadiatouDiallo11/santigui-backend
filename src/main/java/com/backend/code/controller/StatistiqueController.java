package com.backend.code.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.code.dtos.DashboardExploitantDTO;
import com.backend.code.services.StatistiqueService;

@RestController
@RequestMapping("/api/statistiques")
@Tag(name = "Statistiques",
     description = "API des tableaux de bord statistiques")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @Operation(summary = "Dashboard exploitant")
    @GetMapping("/exploitant/{exploitantId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EXPLOITANT')")
    public DashboardExploitantDTO dashboardExploitant(
            @PathVariable Long exploitantId) {

        return statistiqueService.getDashboardExploitant(exploitantId);
    }
}