
package com.example.Event.Controller;
import org.springframework.http.HttpStatus;
import com.example.Event.dto.UserDTO;
import com.example.Event.Model.User;
import com.example.Event.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Validated @RequestBody UserDTO signupRequest) {
        try {
            User newUser = userService.registerUser(signupRequest);
            return ResponseEntity.ok("Utilisateur créé avec succès : " + newUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Tentative de connexion pour : " + request.getEmail());
        try {
            String token = userService.login(request.getEmail(), request.getPassword());
            System.out.println("Connexion réussie, token généré.");
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // DTO pour la requête
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // DTO pour la réponse
    public static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
