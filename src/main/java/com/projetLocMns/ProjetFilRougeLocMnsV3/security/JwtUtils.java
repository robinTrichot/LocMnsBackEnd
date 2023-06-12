package com.projetLocMns.ProjetFilRougeLocMnsV3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtUtils {

    @Value("${jwt.hidden}")
    String jwtHidden;

    public String generateJwt(MyUserDetails myUserDetails) {

        Map<String, Object> data = new HashMap<>();
        data.put("login", myUserDetails.getUsager().getLogin());
        data.put("role", myUserDetails.getUsager().getRole().getRole());
        data.put("lastname", myUserDetails.getUsager().getLastname());
        data.put("firstname", myUserDetails.getUsager().getFirstname());
        data.put("nomImageProfil", myUserDetails.getUsager().getNomImageProfil());
        data.put("idTest", myUserDetails.getUsager().getId());

        return Jwts.builder()
                .setClaims(data)
                .setSubject(myUserDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, "azerty")
                .compact();
    }

    public Claims getData(String jwt) {
        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValide(String jwt) {
        try {
            Claims data = getData(jwt);
        } catch (SignatureException e) {
            return false;
        }
        return true;
    }
}
