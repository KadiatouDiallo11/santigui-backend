package com.backend.code.repository;

import com.backend.code.entity.tables.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, String> {
}