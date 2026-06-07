package com.backend.code.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.code.entity.enums.NiveauAdmin;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.repository.UtilisateurRepository;

@Component
@Order(1)
public class SuperAdminSeeder implements CommandLineRunner {

    private static final String DEFAULT_EMAIL = "superadmin@santigui.local";
    private static final String DEFAULT_PASSWORD = "12345678";

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperAdminSeeder(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (utilisateurRepository.findByEmail(DEFAULT_EMAIL).isPresent()) {
            return;
        }

        Administrateur superAdmin = new Administrateur();
        superAdmin.setNom("Super");
        superAdmin.setPrenom("Admin");
        superAdmin.setEmail(DEFAULT_EMAIL);
        superAdmin.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        superAdmin.setNiveau(NiveauAdmin.SUPER_ADMIN);
        superAdmin.setActif(true);

        utilisateurRepository.save(superAdmin);

        System.out.println("Seeder : SUPER_ADMIN principal cree avec l'email " + DEFAULT_EMAIL);
    }
}