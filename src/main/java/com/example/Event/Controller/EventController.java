package com.example.Event.Controller;
import com.example.Event.Model.Event;
import com.example.Event.Service.EventService;
import com.example.Event.Security.JwtUtil;
import com.example.Event.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(HttpServletRequest request, @RequestBody EventDTO eventDTO) {
        // Récupérer le token depuis l'en-tête Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            String message = "Token manquant ou invalide";
            System.err.println(message); // Affichage de l'erreur dans la console
            return ResponseEntity.status(403).body(message);
        }

        // Extraire le token et récupérer l'ID utilisateur
        try {
            String token = authHeader.substring(7); // Supprime "Bearer "
            String userId = jwtUtil.extractUserId(token);

            if (userId == null) {
                String message = "Utilisateur non trouvé dans le token";
                System.err.println(message); // Affichage de l'erreur dans la console
                return ResponseEntity.status(403).body(message);
            }

            // Vérification du rôle dans le token
            String role = jwtUtil.extractRole(token);  // Assurez-vous d'extraire le rôle
            if (!"ORGANISATEUR".equals(role)) {
                String message = "Accès refusé: Vous devez être un ORGANISATEUR";
                System.err.println(message); // Affichage de l'erreur dans la console
                return ResponseEntity.status(403).body(message);
            }

            // Si tout est valide, ajouter l'événement
            Event event = eventService.addEvent(eventDTO, Integer.parseInt(userId));
            return ResponseEntity.ok(event);

        } catch (Exception e) {
            // Affichage de l'exception générale
            String message = "Erreur lors de la création de l'événement: " + e.getMessage();
            System.err.println(message); // Affichage de l'erreur dans la console
            return ResponseEntity.status(500).body(message);
        }
    }
}
