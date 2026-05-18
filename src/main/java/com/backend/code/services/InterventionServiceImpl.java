package com.backend.code.services;

import com.backend.code.dtos.InterventionRequestDTO;
import com.backend.code.dtos.InterventionResponseDTO;
import com.backend.code.entity.tables.Exploitant;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.repository.ExploitantRepository;
import com.backend.code.repository.InterventionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterventionServiceImpl implements InterventionService {

    private final InterventionRepository interventionRepository;
    private final ExploitantRepository exploitantRepository;

    public InterventionServiceImpl(InterventionRepository interventionRepository,
                                   ExploitantRepository exploitantRepository) {
        this.interventionRepository = interventionRepository;
        this.exploitantRepository = exploitantRepository;
    }

    @Override
    public InterventionResponseDTO create(InterventionRequestDTO dto) {

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        Intervention intervention = new Intervention();
        intervention.setDescription(dto.getDescription());
        intervention.setExploitant(exploitant);

        Intervention saved = interventionRepository.save(intervention);

        return mapToDTO(saved);
    }

    @Override
    public List<InterventionResponseDTO> getAll() {
        return interventionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterventionResponseDTO getById(Long id) {

        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention introuvable"));

        return mapToDTO(intervention);
    }

    @Override
    public InterventionResponseDTO update(Long id, InterventionRequestDTO dto) {

        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention introuvable"));

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        intervention.setDescription(dto.getDescription());
        intervention.setExploitant(exploitant);

        return mapToDTO(interventionRepository.save(intervention));
    }

    @Override
    public void delete(Long id) {
        interventionRepository.deleteById(id);
    }

    private InterventionResponseDTO mapToDTO(Intervention intervention) {
        return new InterventionResponseDTO(
                intervention.getId(),
                intervention.getDescription(),
                intervention.getExploitant().getId(),
                intervention.getExploitant().getNom()
        );
    }
}