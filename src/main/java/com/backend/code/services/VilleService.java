package com.backend.code.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.code.dtos.VilleDTO;
import com.backend.code.entity.tables.Ville;
import com.backend.code.repository.VilleRepository;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public VilleDTO create(VilleDTO dto) {

        Ville ville = new Ville();
        ville.setNomVille(dto.getNomVille());
        ville.setCodePostal(dto.getCodePostal());

        Ville saved = villeRepository.save(ville);

        return mapToDTO(saved);
    }

    public List<VilleDTO> getAll() {
        return villeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public VilleDTO getById(String id) {

        Ville ville = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));

        return mapToDTO(ville);
    }

    public VilleDTO update(String id, VilleDTO dto) {

        Ville ville = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));

        ville.setNomVille(dto.getNomVille());
        ville.setCodePostal(dto.getCodePostal());

        Ville updated = villeRepository.save(ville);

        return mapToDTO(updated);
    }

    public void delete(String id) {
        villeRepository.deleteById(id);
    }

    private VilleDTO mapToDTO(Ville ville) {

        VilleDTO dto = new VilleDTO();

        dto.setId(ville.getId());
        dto.setNomVille(ville.getNomVille());
        dto.setCodePostal(ville.getCodePostal());

        return dto;
    }
}