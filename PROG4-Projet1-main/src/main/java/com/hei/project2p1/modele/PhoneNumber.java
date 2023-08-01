package com.hei.project2p1.modele;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 10, max = 10, message = "Le numéro de téléphone doit avoir 10 chiffres")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    // Getter et Setter pour phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
