package com.backend.code.services;

import com.backend.code.dtos.InterventionRequestDTO;
import com.backend.code.dtos.InterventionResponseDTO;
import com.backend.code.entity.tables.Champ;
import com.backend.code.entity.tables.Exploitant;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.repository.ChampRepository;
import com.backend.code.repository.ExploitantRepository;
import com.backend.code.repository.InterventionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterventionServiceImpl implements InterventionService {

    private final InterventionRepository interventionRepository;
    private final ChampRepository champRepository;
    private final ExploitantRepository exploitantRepository;

    public InterventionServiceImpl(
            InterventionRepository interventionRepository,
            ChampRepository champRepository,
            ExploitantRepository exploitantRepository) {

        this.interventionRepository = interventionRepository;
        this.champRepository = champRepository;
        this.exploitantRepository = exploitantRepository;
    }

    @Override
    public InterventionResponseDTO create(InterventionRequestDTO dto) {

        Champ champ = champRepository.findById(dto.getChampId())
                .orElseThrow(() -> new RuntimeException("Champ introuvable"));

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        Intervention intervention = new Intervention();

        intervention.setChamp(champ);
        intervention.setExploitant(exploitant);
        intervention.setDateIntervention(dto.getDateIntervention());
        intervention.setTypeIntervention(dto.getTypeIntervention());
        intervention.setNatureAction(dto.getNatureAction());
        intervention.setProduitsUtilises(dto.getProduitsUtilises());
        intervention.setDuree(dto.getDuree());
        intervention.setObservations(dto.getObservations());
        intervention.setStatut(dto.getStatut());

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
    public InterventionResponseDTO getById(String id) {

        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention introuvable"));

        return mapToDTO(intervention);
    }

    @Override
    public InterventionResponseDTO update(String id, InterventionRequestDTO dto) {

        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention introuvable"));

        Champ champ = champRepository.findById(dto.getChampId())
                .orElseThrow(() -> new RuntimeException("Champ introuvable"));

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        intervention.setChamp(champ);
        intervention.setExploitant(exploitant);
        intervention.setDateIntervention(dto.getDateIntervention());
        intervention.setTypeIntervention(dto.getTypeIntervention());
        intervention.setNatureAction(dto.getNatureAction());
        intervention.setProduitsUtilises(dto.getProduitsUtilises());
        intervention.setDuree(dto.getDuree());
        intervention.setObservations(dto.getObservations());
        intervention.setStatut(dto.getStatut());

        Intervention updated = interventionRepository.save(intervention);

        return mapToDTO(updated);
    }

    @Override
    public void delete(String id) {

        interventionRepository.deleteById(id);
    }

    private InterventionResponseDTO mapToDTO(Intervention intervention) {

        return new InterventionResponseDTO(
                intervention.getId(),
                intervention.getChamp().getId(),
                intervention.getChamp().getNom(),
                intervention.getExploitant().getId(),
                intervention.getExploitant().getNom(),
                intervention.getDateIntervention(),
                intervention.getTypeIntervention(),
                intervention.getNatureAction(),
                intervention.getProduitsUtilises(),
                intervention.getDuree(),
                intervention.getObservations(),
                intervention.getStatut(),
                intervention.getDateCreation(),
                intervention.getDateModification()
        );
    }
}