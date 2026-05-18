package com.backend.code.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final Environment environment;

    public StartupConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {

        String port = environment.getProperty("local.server.port");

        if (port == null) {
            port = environment.getProperty("server.port", "8080");
        }

        System.out.println("\n====================================");
        System.out.println("🚀 APPLICATION DEMARRÉE AVEC SUCCÈS");
        System.out.println("====================================");

        System.out.println("📌 Swagger UI : http://localhost:" + port + "/swagger-ui.html");
        System.out.println("📌 H2 Console : http://localhost:" + port + "/h2-console");

        System.out.println("====================================\n");
    }
}