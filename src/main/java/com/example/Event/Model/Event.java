package com.example.Event.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;
    private String titre;
    private String lieu;
    private LocalDate dateEvent;
    private int capacite;


    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Organisateur organisateur;


    public Event() {}

    public Event(String titre, String lieu, LocalDate dateEvent, int capacite) {
        this.titre = titre;
        this.lieu = lieu;
        this.dateEvent = dateEvent;
        this.capacite = capacite;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }


}
