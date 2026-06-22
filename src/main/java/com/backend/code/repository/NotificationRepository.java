package com.backend.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.code.entity.tables.Notification;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> findByUtilisateurIdOrderByDateCreationDesc(String utilisateurId);

    void deleteByUtilisateurId(String utilisateurId);
}