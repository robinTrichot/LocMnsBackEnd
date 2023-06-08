package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.StructureDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Structure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.services.FileService;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public class StructureController {

    @Autowired
    StructureDao structureDao;

    @Autowired
    private FileService fileService;

    @GetMapping("/structures")
    @JsonView(ViewStructure.class)
    public List<Structure> getStructure() {
        List structures = structureDao.findAll();
        return structures;
    }

    @GetMapping("structure/{id}")
    @JsonView(ViewStructure.class)
    public ResponseEntity<Structure> getStructures(@PathVariable int id) {
        Optional<Structure> optionalStructure = structureDao.findById(id);
        if (optionalStructure.isPresent()) {
            return new ResponseEntity<>(optionalStructure.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("admin/addStructure")
    public ResponseEntity<Structure> addStructure(@RequestPart("structure") Structure newStructure, @Nullable @RequestParam("fichier") MultipartFile fichier) {
        if (newStructure.getId() != null) {
            Optional<Structure> optionalStructure = structureDao.findById((newStructure.getId()));

            if (optionalStructure.isPresent()) {

                Structure structureToUpdate = optionalStructure.get();
                structureToUpdate.setWording(newStructure.getWording());
                structureToUpdate.setPhone(newStructure.getPhone());
                structureToUpdate.setType(newStructure.getType());
                structureToUpdate.setStreetNumber(newStructure.getStreetNumber());
                structureToUpdate.setNameStreet(newStructure.getNameStreet());
                structureToUpdate.setPostalCode(newStructure.getPostalCode());
                structureToUpdate.setCity(newStructure.getCity());

                structureDao.save(structureToUpdate);

                if (fichier != null) {
                    try {
                        fileService.transfertVersDossierUpload(fichier, "picture-structure");
                    } catch (IOException e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<>(newStructure, HttpStatus.OK);
            }
            return new ResponseEntity<>(newStructure, HttpStatus.BAD_REQUEST);
        }


        if (fichier != null) {
            try {
                String pictureName = UUID.randomUUID() + "_" + fichier.getOriginalFilename();
                newStructure.setPicture(pictureName);
                fileService.transfertVersDossierUpload(fichier, pictureName);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        structureDao.save(newStructure);
        return new ResponseEntity<>(newStructure, HttpStatus.CREATED);
    }

    @GetMapping("/picture-structure/{idStructure}")
    public ResponseEntity<byte[]> getPictureStructure(@PathVariable int idStructure) {
        Optional<Structure> optionalStructure = structureDao.findById(idStructure);

        if(optionalStructure.isPresent()){
            String pictureName = optionalStructure.get().getPicture();
            try  {
                byte[] picture = fileService.getImageByName(pictureName);
                HttpHeaders enTete = new HttpHeaders();
                String mimeType = Files.probeContentType(new File(pictureName).toPath());
                enTete.setContentType(MediaType.valueOf(mimeType));
                return  new ResponseEntity<>(picture, enTete, HttpStatus.OK);
            } catch (FileNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (IOException e) {
                System.out.println("le test du mimeType a échoué");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/deleteStructure/{id}")
    @JsonView(ViewStructure.class)
    public ResponseEntity<Structure> deleteStructure(@PathVariable int id) {
        Optional<Structure> structureToDelete = structureDao.findById(id);
        if (structureToDelete.isPresent()) {
            structureDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}