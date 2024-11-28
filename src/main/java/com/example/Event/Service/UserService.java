package com.example.Event.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.Event.Security.JwtUtil;
import com.example.Event.dto.UserDTO;
import com.example.Event.Model.User;
import com.example.Event.Model.User.Role;
import com.example.Event.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Nécessite Spring Security

    public User registerUser(UserDTO signupRequest) {
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        // Créer un utilisateur
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setNom(signupRequest.getNom());
        user.setTel(signupRequest.getTel());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // Hash du mot de passe

        // Convertir le rôle en majuscule
        String role = signupRequest.getRole().toUpperCase();  // Conversion en majuscule
        try {
            user.setRole(Role.valueOf(role)); // Assigner à l'énumération Role
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rôle invalide, veuillez choisir 'PARTICIPANT' ou 'ORGANISATEUR'.");
        }

        // Sauvegarder l'utilisateur
        return userRepository.save(user);
    }


    @Autowired
    private JwtUtil jwtUtil;

    // Méthode de connexion

    public String login(String email, String password) {
        System.out.println("Tentative de connexion avec email : " + email);

        // Vérifier si l'utilisateur existe
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        // Log de confirmation de la récupération de l'utilisateur
        System.out.println("Utilisateur trouvé : " + user.getEmail());

        // Vérification du mot de passe
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.err.println("Mot de passe incorrect pour l'utilisateur : " + email);
            throw new IllegalArgumentException("Mot de passe incorrect.");
        }

        // Log avant génération du token
        System.out.println("Mot de passe correct pour l'utilisateur : " + email);

        // Convertir le rôle en majuscules pour correspondre à l'énumération
        String roleName = user.getRole().name().toUpperCase();  // Assurez-vous que le rôle est en majuscule

        // Générer le token JWT
        String token = jwtUtil.generateToken(user.getEmail(), roleName,user.getId());

        // Log du token généré
        System.out.println("Token généré avec succès pour l'utilisateur : " + email);

        return token;
    }


}
