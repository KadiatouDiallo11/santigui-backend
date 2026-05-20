package com.backend.code.services;

import com.backend.code.dtos.ChampRequestDTO;
import com.backend.code.dtos.ChampResponseDTO;
import com.backend.code.entity.tables.Champ;
import com.backend.code.entity.tables.Exploitant;
import com.backend.code.repository.ChampRepository;
import com.backend.code.repository.ExploitantRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService {

    private final ChampRepository champRepository;
    private final ExploitantRepository exploitantRepository;

    public ChampServiceImpl(ChampRepository champRepository,
                            ExploitantRepository exploitantRepository) {
        this.champRepository = champRepository;
        this.exploitantRepository = exploitantRepository;
    }

    @Override
    public ChampResponseDTO create(ChampRequestDTO dto) {

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        Champ champ = new Champ();

        champ.setNom(dto.getNom());
        champ.setSuperficie(dto.getSuperficie());
        champ.setTypeCulture(dto.getTypeCulture());
        champ.setCoordonneesGps(dto.getCoordonneesGps());
        champ.setExploitant(exploitant);

        Champ saved = champRepository.save(champ);

        return mapToDTO(saved);
    }

    @Override
    public List<ChampResponseDTO> getAll() {

        return champRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChampResponseDTO getById(Long id) {

        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable"));

        return mapToDTO(champ);
    }

    @Override
    public ChampResponseDTO update(Long id, ChampRequestDTO dto) {

        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable"));

        Exploitant exploitant = exploitantRepository.findById(dto.getExploitantId())
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));

        champ.setNom(dto.getNom());
        champ.setSuperficie(dto.getSuperficie());
        champ.setTypeCulture(dto.getTypeCulture());
        champ.setCoordonneesGps(dto.getCoordonneesGps());
        champ.setExploitant(exploitant);

        Champ updated = champRepository.save(champ);

        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {

        champRepository.deleteById(id);
    }

    private ChampResponseDTO mapToDTO(Champ champ) {

        return new ChampResponseDTO(
                champ.getId(),
                champ.getNom(),
                champ.getSuperficie(),
                champ.getTypeCulture(),
                champ.getExploitant().getId(),
                champ.getExploitant().getNom(),
                champ.getCoordonneesGps()
        );
    }
}