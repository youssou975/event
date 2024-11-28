package com.example.Event.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ORGANISATEUR") // Valeur pour la colonne role
public class Organisateur extends User {

    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    // Constructeur par défaut
    public Organisateur() {}

    // Getter pour les événements
    public List<Event> getEvents() {
        return events;
    }

    // Setter pour les événements
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // Méthode pour ajouter un événement
    public void addEvent(Event event) {
        // Si l'événement n'est pas déjà dans la liste, l'ajouter
        if (!this.events.contains(event)) {
            this.events.add(event);
            event.setOrganisateur(this);
        }
    }

    // Méthode pour supprimer un événement
    public void removeEvent(Event event) {
        if (this.events.contains(event)) {
            this.events.remove(event);
            event.setOrganisateur(null);
        }
    }
}
