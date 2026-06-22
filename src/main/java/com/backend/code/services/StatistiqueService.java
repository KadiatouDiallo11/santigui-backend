package com.backend.code.services;

import com.backend.code.dtos.DashboardAdministrateurDTO;
import com.backend.code.dtos.DashboardExploitantDTO;

public interface StatistiqueService {

    DashboardExploitantDTO getDashboardExploitant(String exploitantId);

    DashboardAdministrateurDTO getDashboardAdministrateur(String administrateurId);
}