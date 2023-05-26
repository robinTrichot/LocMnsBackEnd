package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.HireDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Hire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@CrossOrigin
public class HireController {

    @Autowired
    HireDao hireDao;

    @PostMapping("/commande")
    public ResponseEntity<Hire> createHire(@RequestBody Hire hire) {

        if (hire.getId() != null) {

            Optional<Hire> optional = hireDao.findById(hire.getId());

            if (optional.isPresent()) {
                Hire hireToUpdate = optional.get();
                hireToUpdate.setDateHire(hire.getDateHire());
                hireToUpdate.setDatePlannedReturn(hire.getDatePlannedReturn());

                hireToUpdate.setCopy(hire.getCopy());

                hireDao.save(hire);

                return new ResponseEntity<>(hire, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.hireDao.save(hire);
        return new ResponseEntity<>(hire, HttpStatus.OK);
    }

}
