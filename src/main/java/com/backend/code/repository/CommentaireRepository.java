
package com.backend.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.code.entity.tables.Commentaire;

public interface CommentaireRepository
        extends JpaRepository<Commentaire, String> {

}

