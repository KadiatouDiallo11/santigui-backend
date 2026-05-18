package com.backend.code.dtos;

public class InterventionResponseDTO {

    private Long id;

    private String description;

    private Long exploitantId;

    private String nomExploitant;

    public InterventionResponseDTO() {
    }

    public InterventionResponseDTO(Long id,
                                   String description,
                                   Long exploitantId,
                                   String nomExploitant) {
        this.id = id;
        this.description = description;
        this.exploitantId = exploitantId;
        this.nomExploitant = nomExploitant;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getExploitantId() {
        return exploitantId;
    }

    public String getNomExploitant() {
        return nomExploitant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExploitantId(Long exploitantId) {
        this.exploitantId = exploitantId;
    }

    public void setNomExploitant(String nomExploitant) {
        this.nomExploitant = nomExploitant;
    }
}