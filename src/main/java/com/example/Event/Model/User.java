package com.example.Event.Model;

import jakarta.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Une seule table pour User et ses sous-classes
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING) // Colonne discriminante
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String nom;
    private String tel;
    private String password;

    @Enumerated(EnumType.STRING) // Stocke les valeurs de l'enum comme chaînes de caractères
    private Role role;

    public enum Role {
        Participant,
        Organisateur
    }

    public User() {}

    public User(int id, String email, String nom, String tel, String password, Role role) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.tel = tel;
        this.password = password;
        this.role = role;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
