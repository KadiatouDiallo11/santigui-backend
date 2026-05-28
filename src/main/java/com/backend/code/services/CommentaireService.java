
package com.backend.code.services;

import java.util.List;

import com.backend.code.dtos.CommentaireRequestDTO;
import com.backend.code.dtos.CommentaireResponseDTO;

public interface CommentaireService {

    CommentaireResponseDTO create(CommentaireRequestDTO dto);

    List<CommentaireResponseDTO> getAll();

    CommentaireResponseDTO getById(String id);

    void delete(String id);
}
