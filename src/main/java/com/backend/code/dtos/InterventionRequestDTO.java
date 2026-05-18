package com.backend.code.dtos;

public class InterventionRequestDTO {

    private String description;

    private Long exploitantId;

    public InterventionRequestDTO() {
    }

    public String getDescription() {
        return description;
    }

    public Long getExploitantId() {
        return exploitantId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExploitantId(Long exploitantId) {
        this.exploitantId = exploitantId;
    }
}