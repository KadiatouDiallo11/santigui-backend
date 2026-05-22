package com.backend.code.services;

import com.backend.code.dtos.DashboardExploitantDTO;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.repository.InterventionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatistiqueServiceImpl implements StatistiqueService {

    private final InterventionRepository interventionRepository;

    public StatistiqueServiceImpl(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    @Override
    public DashboardExploitantDTO getDashboardExploitant(Long exploitantId) {

        List<Intervention> interventions =
                interventionRepository.findAll()
                        .stream()
                        .filter(i -> i.getExploitant().getId().equals(exploitantId))
                        .toList();

        DashboardExploitantDTO dto = new DashboardExploitantDTO();

        LocalDate now = LocalDate.now();

        dto.setTotal30Jours(
                interventions.stream()
                        .filter(i -> i.getDateIntervention()
                                .isAfter(now.minusDays(30)))
                        .count()
        );

        dto.setTotal3Mois(
                interventions.stream()
                        .filter(i -> i.getDateIntervention()
                                .isAfter(now.minusMonths(3)))
                        .count()
        );

        dto.setTotalAnnee(
                interventions.stream()
                        .filter(i -> i.getDateIntervention()
                                .getYear() == now.getYear())
                        .count()
        );

        double heures =
                interventions.stream()
                        .mapToInt(Intervention::getDuree)
                        .sum() / 60.0;

        dto.setTempsCumuleHeures(heures);

        Map<String, Long> parType =
                interventions.stream()
                        .collect(Collectors.groupingBy(
                                i -> i.getTypeIntervention().name(),
                                Collectors.counting()
                        ));

        dto.setRepartitionParType(parType);

        Map<String, Long> parChamp =
                interventions.stream()
                        .collect(Collectors.groupingBy(
                                i -> i.getChamp().getNom(),
                                Collectors.counting()
                        ));

        dto.setRepartitionParChamp(parChamp);

        Map<String, Long> evolution =
                interventions.stream()
                        .collect(Collectors.groupingBy(
                                i -> i.getDateIntervention()
                                        .getMonth()
                                        .name(),
                                Collectors.counting()
                        ));

        dto.setEvolutionMensuelle(evolution);

        return dto;
    }
}