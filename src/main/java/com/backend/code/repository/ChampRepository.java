package com.backend.code.repository;

import com.backend.code.entity.tables.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampRepository extends JpaRepository<Champ, Long> {
}