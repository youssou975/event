package com.example.Event.Model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Participant")
public class Participant extends User{

}
