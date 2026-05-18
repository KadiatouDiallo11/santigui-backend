package com.backend.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.code.entity.tables.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

}
