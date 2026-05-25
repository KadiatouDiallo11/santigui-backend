package com.backend.code.services;

import java.util.List;

import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;

public interface UtilisateurService {

	UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto);

    List<UtilisateurResponseDTO> findAll();

    UtilisateurResponseDTO findById(String id);

    void delete(String id);
    
    UtilisateurResponseDTO update(String id, UtilisateurRequestDTO dto);

    UtilisateurResponseDTO activerCompte(String id);

    UtilisateurResponseDTO desactiverCompte(String id);
}
