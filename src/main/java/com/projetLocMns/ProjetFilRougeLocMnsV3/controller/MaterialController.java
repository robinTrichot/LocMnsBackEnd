package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.MaterialDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Material;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Structure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.TrademarkMaterial;
import com.projetLocMns.ProjetFilRougeLocMnsV3.services.FileService;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.File;

@RestController
@CrossOrigin
public class MaterialController {

    @Autowired
    MaterialDao materialDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/materials")
    @JsonView(ViewMaterial.class)
    public List<Material> getCopy() {
        List materials= materialDao.findAll();
        return materials;
    }

    @GetMapping("/materialById/{id}")
    @JsonView(ViewMaterial.class)
    public ResponseEntity<Material> getMaterial(@PathVariable int id) {
        Optional<Material> optionalMaterial = materialDao.findById(id);
        if (optionalMaterial.isPresent()) {
            return new ResponseEntity<>(optionalMaterial.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addMaterial")
    public ResponseEntity<Material> addMaterial(
            @RequestPart("material") Material newMaterial,
            @Nullable @RequestParam("fichier") MultipartFile fichier
    ) {
        if (newMaterial.getId() != null) {
            Optional<Material> optionalMaterial = materialDao.findById(newMaterial.getId());
            if (optionalMaterial.isPresent()) {
                Material materialToUpdate = optionalMaterial.get();
                materialToUpdate.setWording(newMaterial.getWording());
                materialToUpdate.setStructure(optionalMaterial.get().getStructure());
                materialToUpdate.setTrademarkMaterial(optionalMaterial.get().getTrademarkMaterial());

                materialDao.save(materialToUpdate);

                if (fichier != null) {
                    try {
                        fileService.transfertVersDossierUpload(fichier, "material-picture");
                    } catch (IOException e) {
                        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<>(newMaterial, HttpStatus.OK);
            }
            return new ResponseEntity<>(newMaterial, HttpStatus.BAD_REQUEST);
        }
        TrademarkMaterial trademarkMaterial = new TrademarkMaterial();
        trademarkMaterial.setId(1);
        newMaterial.setTrademarkMaterial(trademarkMaterial);

        Structure structure = new Structure();
        structure.setId(1);
        newMaterial.setStructure(structure);

        if (fichier != null) {
            try {
                String pictureName = UUID.randomUUID() + "_" + fichier.getOriginalFilename();
                newMaterial.setPicture(pictureName);
                fileService.transfertVersDossierUpload(fichier, pictureName);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        materialDao.save(newMaterial);
        return new ResponseEntity<>(newMaterial, HttpStatus.CREATED);
    }

    @GetMapping("/picture-material/{idMaterial}")
    public ResponseEntity<byte[]> getPictureMaterial(@PathVariable int idMaterial) {
        Optional<Material> optionalMaterial = materialDao.findById(idMaterial);

        if(optionalMaterial.isPresent()){
            String pictureName = optionalMaterial.get().getPicture();
            try  {
                byte[] picture = fileService.getImageByName(pictureName);
                HttpHeaders enTete = new HttpHeaders();
                String mimeType = Files.probeContentType(new File(pictureName).toPath());
                enTete.setContentType(MediaType.valueOf(mimeType));
                return  new ResponseEntity<>(picture, enTete, HttpStatus.OK);
            } catch (FileNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (IOException e) {
                System.out.println("le test du mineType a échoué");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/deleteMaterial/{id}")
    @JsonView(ViewMaterial.class)
    public ResponseEntity<Material> deleteMaterial(@PathVariable int id){
        Optional<Material> materialToDelete = materialDao.findById(id);
        if(materialToDelete.isPresent()){
            materialDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
