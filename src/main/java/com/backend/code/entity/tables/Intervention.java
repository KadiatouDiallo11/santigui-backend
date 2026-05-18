package com.backend.code.entity.tables;

import jakarta.persistence.*;

@Entity
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "exploitant_id")
    private Exploitant exploitant;

    public Intervention() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exploitant getExploitant() {
        return exploitant;
    }

    public void setExploitant(Exploitant exploitant) {
        this.exploitant = exploitant;
    }
}