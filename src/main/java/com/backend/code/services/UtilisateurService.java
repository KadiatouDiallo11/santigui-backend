package com.backend.code.services;

import java.util.List;

import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;

public interface UtilisateurService {

	UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto);

    List<UtilisateurResponseDTO> findAll();

    UtilisateurResponseDTO findById(Long id);

    void delete(Long id);
    
    UtilisateurResponseDTO update(Long id, UtilisateurRequestDTO dto);
}
