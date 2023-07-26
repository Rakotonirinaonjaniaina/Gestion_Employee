package com.hei.project2p1.modele;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entreprise")
@Getter
@Setter
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email_contact")
    private String emailContact;

    @ElementCollection
    @CollectionTable(name = "entreprise_phones", joinColumns = @JoinColumn(name = "entreprise_id"))
    @Column(name = "phone")
    private List<String> phones;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "STAT")
    private String stat;

    @Column(name = "RCS")
    private String rcs;

    @Column(name = "matricule")
    private String matricule;


    // Constructeur, getters et setters
}
