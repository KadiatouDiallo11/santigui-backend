
package com.backend.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.code.entity.tables.Administrateur;

public interface AdministrateurRepository
        extends JpaRepository<Administrateur, String> {

}
