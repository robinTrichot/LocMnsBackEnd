package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.CopyDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
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

    @GetMapping("/copies")
    public List<Copy> getCopy() {
        List copies = copyDao.findAll();
        return copies;
    }

    @GetMapping("/copie/{id}")
    public ResponseEntity<Copy> getCopy(@PathVariable int id) {

        Optional<Copy> optional = copyDao.findById(id);

        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/copiesDispo")
    @JsonView(ViewCopy.class)
    public List<Object[]> getStatus() {
        return copyDao.findByStatus();
    }

    // voir projection
    // créer un itnerface
    // pour renvoyer un objet manipulable


    //renvoit les exemplaires lorsque l'on choisi un matériel en question
    @GetMapping("/material/{wording}")
    public List<Copy> trouverExemplairesParMateriel(@PathVariable String wording) {
        return copyDao.findByWording(wording);
    }

    //renvoit les exemplaires lorsque l'on choisi un matériel en question par l'id;
    @GetMapping("/material/id/{id}")
    public List<Copy> trouverExemplairesParMaterielId(@PathVariable Integer id) {
        return copyDao.findCopyByMaterialId(id);
    }

    @PostMapping("/change/copy")
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
}
