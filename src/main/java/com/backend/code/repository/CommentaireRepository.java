package com.backend.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.code.entity.tables.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, String> {

}
