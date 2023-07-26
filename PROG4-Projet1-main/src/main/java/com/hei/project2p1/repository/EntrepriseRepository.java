package com.hei.project2p1.repository;

import com.hei.project2p1.modele.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire
}
