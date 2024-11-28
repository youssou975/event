package com.example.Event.Service;

import com.example.Event.Model.Event;
import com.example.Event.Model.Organisateur;
import com.example.Event.Repository.EventRepository;
import com.example.Event.Repository.UserRepository;
import com.example.Event.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Event addEvent(EventDTO eventDTO, int organisateurId) {
        // Trouver l'organisateur par ID
        Organisateur organisateur = (Organisateur) userRepository.findById(organisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Organisateur non trouvé"));

        // Créer un nouvel événement
        Event event = new Event();
        event.setTitre(eventDTO.getTitre());
        event.setLieu(eventDTO.getLieu());
        event.setDateEvent(eventDTO.getDateEvent());
        event.setCapacite(eventDTO.getCapacite());

        // Lier l'événement à l'organisateur
        event.setOrganisateur(organisateur);

        // Sauvegarder l'événement
        return eventRepository.save(event);
    }
}
