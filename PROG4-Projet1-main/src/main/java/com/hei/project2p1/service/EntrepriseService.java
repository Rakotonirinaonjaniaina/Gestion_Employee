package com.hei.project2p1.service;

import com.hei.project2p1.modele.Entreprise;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EntrepriseService {
    private Entreprise entreprise;

    public EntrepriseService() {
        entreprise = new Entreprise();
        entreprise.setNom("Mon entreprise");
        entreprise.setDescription("Description de mon entreprise");
        entreprise.setSlogan("Slogan de mon entreprise");
        entreprise.setAdresse("Adresse de mon entreprise");
        entreprise.setEmailContact("contact@monentreprise.com");
        entreprise.setPhones(Arrays.asList("0123456789", "9876543210"));
        entreprise.setNif("1234567890");
        entreprise.setStat("0987654321");
        entreprise.setRcs("1357924680");
        entreprise.setMatricule("MAT12345"); // Ajout du matricule
    }

    public Entreprise getEntrepriseDetails() {
        return entreprise;
    }

    public void saveEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
