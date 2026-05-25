package com.backend.code.services;

import com.backend.code.dtos.InterventionRequestDTO;
import com.backend.code.dtos.InterventionResponseDTO;

import java.util.List;

public interface InterventionService {

    InterventionResponseDTO create(InterventionRequestDTO dto);

    List<InterventionResponseDTO> getAll();

    InterventionResponseDTO getById(String id);

    InterventionResponseDTO update(String id, InterventionRequestDTO dto);

    void delete(String id);
}