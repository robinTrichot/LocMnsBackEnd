package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.StructureDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.UserDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Structure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import com.projetLocMns.ProjetFilRougeLocMnsV3.services.FileService;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileService fileService;

    @Autowired
    private StructureDao structureDao;

    @GetMapping("/user/usagers")
    @JsonView(ViewUser.class)
    public List<User> getUsers() {
        List usagers = userDao.findAll();
        return usagers;
    }

    @GetMapping("/user/usager/{id}")
    @JsonView(ViewUser.class)
    public ResponseEntity<User> getUtilisateur(@PathVariable int id) {

        //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        Optional<User> optional = userDao.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addUsager")
    public ResponseEntity<User> addUsager(@RequestPart("usager") User newUser, @Nullable @RequestParam("fichier") MultipartFile fichier) {

        Optional<User> optional = userDao.findByMail(newUser.getMail());

        if (optional.isPresent()) {
            return new ResponseEntity<>(newUser, HttpStatus.CONFLICT);
        }
        String passwordHache = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(passwordHache);

        if (fichier != null) {
            try {
                String nomImage = UUID.randomUUID() + "_" + fichier.getOriginalFilename();
                newUser.setNomImageProfil(nomImage);
                fileService.transfertVersDossierUpload(fichier, nomImage);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        userDao.save(newUser); // Enregistrer l'utilisateur

// Mettre à jour la table de liaison many-to-many
        Set<Structure> selectedStructures = newUser.getStructures();
        for (Structure structure : selectedStructures) {
            structure.getUsers().add(newUser);
            structureDao.save(structure); // Sauvegarder chaque structure mise à jour
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PutMapping("/admin/putUsager")
    public ResponseEntity<User> putUsager(@RequestPart("usager") User newUser, @Nullable @RequestParam("fichier") MultipartFile fichier) {

        // Je fais un premier test pour vérifier si l'adresse e-mail est déjà prise
        Optional<User> optional = userDao.findByMail(newUser.getMail());

        if (optional.isPresent() && !optional.get().getId().equals(newUser.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Adresse e-mail déjà utilisée dans la bdd
        }

        // Je fais une manipulation pour récuperer par l'id cette fois-ici l'utilisateur que je veux modifier
        // car l'admin peut vouloir changer son adresse mail, je ne dois pas faire une recheche pas mail !
        Optional<User> optionalbis = userDao.findById(newUser.getId());

        if (optionalbis.isPresent()) {
            User userToUpdate = optionalbis.get();
            userToUpdate.setPassword(optionalbis.get().getPassword()); // récupération du mot de passe initial(ne pas le modifier)
            userToUpdate.setLastname(newUser.getLastname());
            userToUpdate.setFirstname(newUser.getFirstname());
            userToUpdate.setPhone(newUser.getPhone());
            userToUpdate.setCellPhone(newUser.getCellPhone());
            userToUpdate.setMail(newUser.getMail());
            userToUpdate.setStreetNumber(newUser.getStreetNumber());
            userToUpdate.setNameStreet(newUser.getNameStreet());
            userToUpdate.setPostalCode(newUser.getPostalCode());
            userToUpdate.setCity(newUser.getCity());
            userToUpdate.setRole(newUser.getRole());

            if (fichier != null) {
                try {
                    fileService.transfertVersDossierUpload(fichier, "image-profil");
                } catch (IOException e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            userDao.save(userToUpdate);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/image-profil/{idUtilisateur}")
    public ResponseEntity<byte[]> getImageProfil(@PathVariable int idUtilisateur) {

        Optional<User> optional = userDao.findById(idUtilisateur);

        // on vérifie l'utilisateur
        if (optional.isPresent()) {
            String nomImage = optional.get().getNomImageProfil();

            // donc je créer un tableau de byte qui vient recueprer cette image
            // donc en tranforme ici
            // il faut faire un try catch;
            // si jamais il trouve pas l'image, on retourne nous même un 404
            // et on fait nous même un responseEntity en 404 not found dans le catch;
            try {
                byte[] image = fileService.getImageByName(nomImage);

                // on va devoir changer l'entete, bien dire que c'est pas du JSON, c'est une image
                // donc c'est pas une image attention, on chnage le content-type (le contenu de lobjet)

                // donc j'en crée un en plus;
                HttpHeaders enTete = new HttpHeaders();
                //on verifier le mineType (le type de fichier)
                // on créer une instance de type avec le nom du fichier;
                // chaque type fichier à son mineType,
                String mimeType = Files.probeContentType(new File(nomImage).toPath());
                enTete.setContentType(MediaType.valueOf(mimeType));

                // on renvoit don, notre image tranformée + l'entete, + le statut de la requete;
                return new ResponseEntity<>(image, enTete, HttpStatus.OK);

            } catch (FileNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (IOException e) {
                System.out.println("le test du mineType a échoué");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/deleteUsager/{id}")
    public ResponseEntity<User> supprimeUtilisateur(@PathVariable int id) {

        Optional<User> usagerToDelete = userDao.findById(id);

        if (usagerToDelete.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}