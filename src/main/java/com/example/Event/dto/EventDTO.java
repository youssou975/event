package com.example.Event.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EventDTO {
    private String titre;
    private String lieu;
    @DateTimeFormat(pattern = "dd-MM-yyyy") // Format de la date attendu

    private LocalDate dateEvent;
    private int capacite;

    // Getters et setters
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
