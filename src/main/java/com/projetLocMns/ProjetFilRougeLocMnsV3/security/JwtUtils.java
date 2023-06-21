package com.projetLocMns.ProjetFilRougeLocMnsV3.security;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtUtils {

    @Value("${jwt.hidden}")
    String jwtHidden;

    private static final long EXPIRATION_TOKEN = 660 * 1000; // 10 minutes (en millisecondes)

    public String generateJwt(MyUserDetails myUserDetails) {

        Map<String, Object> data = new HashMap<>();
        data.put("mail", myUserDetails.getUsager().getMail());
        data.put("role", myUserDetails.getUsager().getRole().getRole());
        data.put("lastname", myUserDetails.getUsager().getLastname());
        data.put("firstname", myUserDetails.getUsager().getFirstname());
        data.put("nomImageProfil", myUserDetails.getUsager().getNomImageProfil());
        data.put("idTest", myUserDetails.getUsager().getId());

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TOKEN);

        return Jwts.builder()
                .setClaims(data)
                .setSubject(myUserDetails.getUsername())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "azerty")
                .compact();
    }

    public Claims getData(String jwt) {
        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValide(String jwt) throws ExpiredTokenException {
        try {
            Claims data = getData(jwt);
        } catch (SignatureException e) {
            return false;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("La durée du jeton n'est plus bonne");
        }
        return true;
    }
}
