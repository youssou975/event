package com.example.Event.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Organisateur") // Valeur "ORGANISATEUR" stockée dans la colonne 'role'
public class Organisateur extends User {
    // Méthodes ou champs spécifiques à l'Organisateur
}
