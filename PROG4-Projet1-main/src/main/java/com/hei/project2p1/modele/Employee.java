package com.hei.project2p1.modele;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "gender")
    private String gender;

    @ElementCollection
    @CollectionTable(name = "employee_phones", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone")
    private List<String> phones;

    @Column(name = "address")
    private String address;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "work_email")
    private String workEmail;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    // Nouveaux attributs ajoutés pour le sujet
    @Column(name = "CIN_number")
    private String CINNumber;

    @Column(name = "CIN_date_of_delivery")
    private String CINDateOfDelivery;

    @Column(name = "CIN_place_of_delivery")
    private String CINPlaceOfDelivery;

    @Column(name = "function")
    private String function;

    @Column(name = "children_count")
    private int childrenCount;

    @Column(name = "hire_date")
    private String hireDate;

    @Column(name = "departure_date")
    private String departureDate;

    @Column(name = "socio_professional_category")
    private String socioProfessionalCategory;

    @Column(name = "CNAPS_number")
    private String CNAPSNumber;

    // Ajoutez les getters et setters pour toutes les propriétés
}
