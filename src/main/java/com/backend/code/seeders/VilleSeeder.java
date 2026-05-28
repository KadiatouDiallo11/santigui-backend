package com.backend.code.seeders;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.backend.code.entity.tables.Ville;
import com.backend.code.repository.VilleRepository;

@Component
@Order(1)
public class VilleSeeder implements CommandLineRunner {

    private final VilleRepository villeRepository;

    public VilleSeeder(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Override
    public void run(String... args) {

        if (villeRepository.count() > 0) {
            return;
        }

        List<Ville> villes = List.of(
            ville("Paris",          "75000"),
            ville("Marseille",      "13000"),
            ville("Lyon",           "69000"),
            ville("Toulouse",       "31000"),
            ville("Nice",           "06000"),
            ville("Nantes",         "44000"),
            ville("Strasbourg",     "67000"),
            ville("Montpellier",    "34000"),
            ville("Bordeaux",       "33000"),
            ville("Lille",          "59000"),
            ville("Rennes",         "35000"),
            ville("Reims",          "51100"),
            ville("Le Havre",       "76600"),
            ville("Saint-Étienne",  "42000"),
            ville("Toulon",         "83000"),
            ville("Grenoble",       "38000"),
            ville("Dijon",          "21000"),
            ville("Angers",         "49000"),
            ville("Nîmes",          "30000"),
            ville("Villeurbanne",   "69100"),
            ville("Montauban",   "65300"),
            ville("Toulouse",   "69600"),
            ville("ville 3", "45900")
        );

        villeRepository.saveAll(villes);

        System.out.println("✅ Seeder : " + villes.size() + " villes insérées.");
    }

    private Ville ville(String nom, String codePostal) {
        Ville v = new Ville();
        v.setNomVille(nom);
        v.setCodePostal(codePostal);
        return v;
    }
}