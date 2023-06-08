package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.TypeUsagerDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.TypeUsager;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewTypeUsager;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TypeUsagerController {


    @Autowired
    TypeUsagerDao typeUsagerDao;
    @GetMapping("/TypeUsagers")
    @JsonView(ViewUser.class)
    public List<User> getTypeUsers() {
        List roles = typeUsagerDao.findAll();
        return roles;
    }
    // la suite on la fait coté front end, j'appelle dans service les roles pour afficher;

    @GetMapping("typeUsager/{id}")
    @JsonView({ViewUser.class})
    public ResponseEntity<TypeUsager> getTypeUsager(@PathVariable int id) {
        Optional<TypeUsager> optionalTypeUsager = typeUsagerDao.findById(id);
        if (optionalTypeUsager.isPresent()) {
            return new ResponseEntity<>(optionalTypeUsager.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addTypeUsager")
    public ResponseEntity<TypeUsager> addTypeUsager(@RequestBody TypeUsager newTypeUsager
    ) {
        if (newTypeUsager.getId() != null) {
            Optional<TypeUsager> optionalTypeUsager = typeUsagerDao.findById(newTypeUsager.getId());
            if (optionalTypeUsager.isPresent())  {
                TypeUsager typeUsagerToUpdate = optionalTypeUsager.get();
                typeUsagerToUpdate.setWording(newTypeUsager.getWording());
                typeUsagerToUpdate.setRole(newTypeUsager.getRole());


                typeUsagerDao.save(typeUsagerToUpdate);

                return new ResponseEntity<>(newTypeUsager, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        typeUsagerDao.save(newTypeUsager);
        return new ResponseEntity<>(newTypeUsager, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteTypeUsager/{id}")
    @JsonView({ViewUser.class, ViewTypeUsager.class})
    public ResponseEntity<TypeUsager> deleteTypeUsager(@PathVariable int id){
        Optional<TypeUsager> typeUsagerToDelete = typeUsagerDao.findById(id);
        if(typeUsagerToDelete.isPresent()){
            typeUsagerDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

