package com.backend.code.services;

import com.backend.code.dtos.ChampRequestDTO;
import com.backend.code.dtos.ChampResponseDTO;

import java.util.List;

public interface ChampService {

    ChampResponseDTO create(ChampRequestDTO dto);

    List<ChampResponseDTO> getAll();

    ChampResponseDTO getById(String id);

    ChampResponseDTO update(String id, ChampRequestDTO dto);

    void delete(String id);
}