package com.example.Event.Security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

    @Component
    public class JwtUtil {

        // Clé secrète mise à jour avec au moins 32 caractères (256 bits)
        private final String secret = "ORTREEAIYOUSSASTOUOUSASTKEYTOSECURE123456780976543";
        private final long expirationMs = 3600000; // Expiration : 1 heure

        private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

        // Génération du token JWT
        public String generateToken(String email, String role, int userId) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }

        // Validation et extraction des claims
        public Claims validateToken(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }


        public String extractUserId(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // Assurez-vous que l'ID ou l'email est bien dans le "subject"
        }


        // Récupérer l'email depuis le token
     /*   public String getEmailFromToken(String token) {
            return validateToken(token).getSubject();
        } */

        public String extractRole(String token) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secret)  // Assurez-vous que vous avez une clé secrète correcte
                        .parseClaimsJws(token)
                        .getBody();
                return claims.get("role", String.class); // Assurez-vous que le rôle est bien un claim
            } catch (Exception e) {
                System.err.println("Erreur lors de l'extraction du rôle depuis le token: " + e.getMessage());
                throw new IllegalArgumentException("Erreur d'extraction du rôle depuis le token");
            }
        }
    }
