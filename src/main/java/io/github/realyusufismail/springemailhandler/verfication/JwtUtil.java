package io.github.realyusufismail.springemailhandler.verfication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import static io.github.realyusufismail.springemailhandler.env.DotenvConfig.dotenv;

public class JwtUtil {
    private static final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
    private static final long EXPIRATION_TIME_MS = 86400000; // 24 hours

    public static String generateToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_MS);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Verifies the token and returns true if the token is valid
     * @param token The token to verify
     * @return True if the token is valid else false
     */
    public static boolean verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject() != null && !claims.getSubject().isBlank() && !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
