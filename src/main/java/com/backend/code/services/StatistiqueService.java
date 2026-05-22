package com.backend.code.services;

import com.backend.code.dtos.DashboardExploitantDTO;

public interface StatistiqueService {

    DashboardExploitantDTO getDashboardExploitant(Long exploitantId);
}