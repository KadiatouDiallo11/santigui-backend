package com.backend.code.services;

import com.backend.code.dtos.InterventionRequestDTO;
import com.backend.code.dtos.InterventionResponseDTO;

import java.util.List;

public interface InterventionService {

    InterventionResponseDTO create(InterventionRequestDTO dto);

    List<InterventionResponseDTO> getAll();

    InterventionResponseDTO getById(Long id);

    InterventionResponseDTO update(Long id, InterventionRequestDTO dto);

    void delete(Long id);
}