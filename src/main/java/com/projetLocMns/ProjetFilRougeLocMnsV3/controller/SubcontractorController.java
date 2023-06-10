package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.SubcontractorDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Subcontractor;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewSubcontractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class SubcontractorController {

    @Autowired
    SubcontractorDao subcontractorDao;

    @GetMapping("/user/subcontractors")
    public List<Subcontractor> getSubcontractors() {
        List subcontractors = subcontractorDao.findAll();
        return subcontractors;
    }

    @GetMapping("/user/subcontractor/{id}")
    @JsonView({ViewSubcontractor.class})
    public ResponseEntity<Subcontractor> getSubcontractor(@PathVariable int id) {
        Optional<Subcontractor> optionalSubcontractor = subcontractorDao.findById(id);
        if (optionalSubcontractor.isPresent()) {
            return new ResponseEntity<>(optionalSubcontractor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addSubcontractor")
    public ResponseEntity<Subcontractor> addSubcontractor(
            @RequestBody Subcontractor newSubcontractor
    ) {
        if (newSubcontractor.getId() != null) {
            Optional<Subcontractor> optionalSubcontractor = subcontractorDao.findById(newSubcontractor.getId());
            if (optionalSubcontractor.isPresent())  {
                Subcontractor subcontractorToUpdate = optionalSubcontractor.get();
                subcontractorToUpdate.setDesignation(newSubcontractor.getDesignation());
                subcontractorToUpdate.setPhone(newSubcontractor.getPhone());
                subcontractorToUpdate.setNumberStreet(newSubcontractor.getNumberStreet());
                subcontractorToUpdate.setNameStreet(newSubcontractor.getNameStreet());
                subcontractorToUpdate.setPostaleCode(newSubcontractor.getPostaleCode());
                subcontractorToUpdate.setCity(newSubcontractor.getCity());

                subcontractorDao.save(subcontractorToUpdate);

                return new ResponseEntity<>(newSubcontractor, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        subcontractorDao.save(newSubcontractor);
        return new ResponseEntity<>(newSubcontractor, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteSubcontractor/{id}")
    @JsonView({ViewSubcontractor.class})
    public ResponseEntity<Subcontractor> deleteSubcontractor(@PathVariable int id){
        Optional<Subcontractor> subcontractorToDelete = subcontractorDao.findById(id);
        if(subcontractorToDelete.isPresent()){
            subcontractorDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}