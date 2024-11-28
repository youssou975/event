package com.example.Event.Model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARTICIPANT")
public class Participant extends User{

}
