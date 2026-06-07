package com.backend.code.services;

import java.util.List;

import com.backend.code.dtos.AdministrateurRequestDTO;
import com.backend.code.dtos.ExploitantRequestDTO;
import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;

public interface UtilisateurService {

	UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto);

	UtilisateurResponseDTO createAdministrateur(AdministrateurRequestDTO dto);

	UtilisateurResponseDTO createExploitant(ExploitantRequestDTO dto);

    List<UtilisateurResponseDTO> findAll();

    List<UtilisateurResponseDTO> findAllAdministrateurs();

    List<UtilisateurResponseDTO> findAllExploitants();

    UtilisateurResponseDTO findById(String id);

    UtilisateurResponseDTO findAdministrateurById(String id);

    UtilisateurResponseDTO findExploitantById(String id);

    void delete(String id);

    void deleteAdministrateur(String id);

    void deleteExploitant(String id);
    
    UtilisateurResponseDTO update(String id, UtilisateurRequestDTO dto);

    UtilisateurResponseDTO updateAdministrateur(String id, AdministrateurRequestDTO dto);

    UtilisateurResponseDTO updateExploitant(String id, ExploitantRequestDTO dto);

    UtilisateurResponseDTO activerCompte(String id);

    UtilisateurResponseDTO activerAdministrateur(String id);

    UtilisateurResponseDTO activerExploitant(String id);

    UtilisateurResponseDTO desactiverCompte(String id);

    UtilisateurResponseDTO desactiverAdministrateur(String id);

    UtilisateurResponseDTO desactiverExploitant(String id);
}
