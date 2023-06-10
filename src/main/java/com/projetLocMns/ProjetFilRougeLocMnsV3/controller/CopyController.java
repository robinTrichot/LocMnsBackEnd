package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.CopyDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Material;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
public class CopyController {

    @Autowired
    CopyDao copyDao;

    @GetMapping("/user/copies")
    public List<Copy> getCopy() {
        List copies = copyDao.findAll();
        return copies;
    }

    @GetMapping("/user/copie/{id}")
    public ResponseEntity<Copy> getCopy(@PathVariable int id) {

        Optional<Copy> optional = copyDao.findById(id);

        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/copiesDispo")
    @JsonView(ViewCopy.class)
    public List<Object[]> getStatus() {
        return copyDao.findByStatus();
    }

    // voir projection
    // créer un itnerface
    // pour renvoyer un objet manipulable


    //renvoit les exemplaires lorsque l'on choisi un matériel en question
    @GetMapping("/user/material/{wording}")
    public List<Copy> trouverExemplairesParMateriel(@PathVariable String wording) {
        return copyDao.findByWording(wording);
    }

    //renvoit les exemplaires lorsque l'on choisi un matériel en question par l'id;
    @GetMapping("/user/material/id/{id}")
    public List<Copy> trouverExemplairesParMaterielId(@PathVariable Integer id) {
        return copyDao.findCopyByMaterialId(id);
    }

    @PostMapping("/user/change/copy")
    public ResponseEntity<Copy> changeStatusCopy(@RequestBody Copy copy) {

        if (copy.getId() != null) {

            Optional<Copy> optional = copyDao.findById(copy.getId());

            if (optional.isPresent()) {
                Copy copyToUpdate = optional.get();
                copyToUpdate.setDateOutOfStock(copy.getDateOutOfStock());
                copyToUpdate.setDatePurchase(copy.getDatePurchase());
                copyToUpdate.setInStock(copy.getInStock());
                copyToUpdate.setSerialNumber(copy.getSerialNumber());
                copyToUpdate.setFeatures(copy.getFeatures());
                copyToUpdate.setMaterial(copy.getMaterial());

                if (Objects.equals(copy.getStatus(), "hired")) {
                    copyToUpdate.setStatus("free");
                } else if (Objects.equals(copy.getStatus(), "free")) {
                    copyToUpdate.setStatus("hired");
                }

                copyDao.save(copyToUpdate);
                return new ResponseEntity<>(copy, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        copyDao.save(copy);
        return new ResponseEntity<>(copy, HttpStatus.OK);
    }
    //Changement status en stock de la copie pour une panne
//    @PostMapping("/changeInStockCopy")
//    public ResponseEntity<Copy> changeInStockCopy(@RequestBody Copy copy) {
//
//        if (copy.getId() != null) {
//
//            Optional<Copy> optional = copyDao.findById(copy.getId());
//
//            if (optional.isPresent()) {
//                Copy copyToUpdate = optional.get();
//                copyToUpdate.setDateOutOfStock(copy.getDateOutOfStock());
//                copyToUpdate.setDatePurchase(copy.getDatePurchase());
//                copyToUpdate.setInStock(copy.getInStock());
//                copyToUpdate.setSerialNumber(copy.getSerialNumber());
//                copyToUpdate.setFeatures(copy.getFeatures());
//                copyToUpdate.setMaterial(copy.getMaterial());
//
//                if (Objects.equals(copy.getInStock(), false)) {
//                    copyToUpdate.setInStock(true);
//                } else if (Objects.equals(copy.getInStock(), true)) {
//                    copyToUpdate.setInStock(false);
//                }
//                copyDao.save(copyToUpdate);
//
//                return new ResponseEntity<>(copy, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        copyDao.save(copy);
//        return new ResponseEntity<>(copy, HttpStatus.OK);
//    }


    @PostMapping("/admin/addCopy")
    public ResponseEntity<Copy> addCopy(
            @RequestPart("copy") Copy newCopy
    ) {
        if (newCopy.getId() != null) {
            Optional<Copy> optionalCopy = copyDao.findById(newCopy.getId());
            if (optionalCopy.isPresent())  {
                Copy copyToUpdate = optionalCopy.get();
                copyToUpdate.setDatePurchase(newCopy.getDatePurchase());
                copyToUpdate.setStatus(newCopy.getStatus());
                copyToUpdate.setDateOutOfStock(newCopy.getDateOutOfStock());
                copyToUpdate.setSerialNumber(newCopy.getSerialNumber());

                copyDao.save(copyToUpdate);

                return new ResponseEntity<>(newCopy, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.copyDao.save(newCopy);
        return new ResponseEntity<>(newCopy, HttpStatus.OK);
    }


    @DeleteMapping("/admin/deleteCopy/{id}")
    @JsonView(ViewCopy.class)
    public ResponseEntity<Material> deleteCopy(@PathVariable int id){
        Optional<Copy> copyToDelete = copyDao.findById(id);
        if(copyToDelete.isPresent()){
            copyDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

