package com.backend.code.services;

import com.backend.code.dtos.DashboardAdministrateurDTO;
import com.backend.code.dtos.DashboardExploitantDTO;
import com.backend.code.entity.tables.Commentaire;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.repository.AdministrateurRepository;
import com.backend.code.repository.CommentaireRepository;
import com.backend.code.repository.ExploitantRepository;
import com.backend.code.repository.InterventionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatistiqueServiceImpl implements StatistiqueService {

    private final InterventionRepository interventionRepository;
        private final ExploitantRepository exploitantRepository;
        private final CommentaireRepository commentaireRepository;
        private final AdministrateurRepository administrateurRepository;

        public StatistiqueServiceImpl(
                        InterventionRepository interventionRepository,
                        ExploitantRepository exploitantRepository,
                        CommentaireRepository commentaireRepository,
                        AdministrateurRepository administrateurRepository) {
        this.interventionRepository = interventionRepository;
                this.exploitantRepository = exploitantRepository;
                this.commentaireRepository = commentaireRepository;
                this.administrateurRepository = administrateurRepository;
    }

    @Override
        public DashboardExploitantDTO getDashboardExploitant(String exploitantId) {

        List<Intervention> interventions =
                interventionRepository.findAll()
                        .stream()
                                                .filter(i -> i.getExploitant() != null)
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

    @Override
    public DashboardAdministrateurDTO getDashboardAdministrateur(String administrateurId) {
        administrateurRepository.findById(administrateurId)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));

        List<Intervention> interventions = interventionRepository.findAll();

        java.util.Set<String> interventionIdsAvecCommentaires = commentaireRepository.findAll().stream()
                .map(Commentaire::getIntervention)
                .filter(intervention -> intervention != null)
                .map(Intervention::getId)
                .collect(Collectors.toSet());

        DashboardAdministrateurDTO dto = new DashboardAdministrateurDTO();
        dto.setNombreExploitantsAjoutes(exploitantRepository.count());
        dto.setRepartitionInterventionsParVille(groupAndSort(interventions,
                intervention -> intervention.getChamp() != null && intervention.getChamp().getVille() != null
                        ? intervention.getChamp().getVille().getNomVille()
                        : "Ville non renseignee"));
        dto.setRepartitionInterventionsParType(groupAndSort(interventions,
                intervention -> intervention.getTypeIntervention() != null
                        ? intervention.getTypeIntervention().name()
                        : "TYPE_NON_RENSEIGNE"));
        dto.setNombreFichesEnAttente(interventions.stream()
                .filter(intervention -> !interventionIdsAvecCommentaires.contains(intervention.getId()))
                .count());
        return dto;
    }

    private Map<String, Long> groupAndSort(List<Intervention> interventions, Function<Intervention, String> classifier) {
        return interventions.stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (left, right) -> left,
                        LinkedHashMap::new));
    }
}