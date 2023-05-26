package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;



import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.UserDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import com.projetLocMns.ProjetFilRougeLocMnsV3.security.JwtUtils;
import com.projetLocMns.ProjetFilRougeLocMnsV3.security.MonUserDetailsService;
import com.projetLocMns.ProjetFilRougeLocMnsV3.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MonUserDetailsService monUserDetailsService;

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody User user) {

        MyUserDetails myUserDetails;
        try {
            myUserDetails = (MyUserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getMail(),
                            user.getPassword()
                    )
            ).getPrincipal();
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(jwtUtils.generateJwt(myUserDetails), HttpStatus.OK);
    }
}
