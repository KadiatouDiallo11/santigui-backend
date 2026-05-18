package com.backend.code.repository;
import com.backend.code.entity.tables.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
}