package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.CopyDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.HireDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.UserDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.enums.StatusHireEnum;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Hire;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class HireController {

    @Autowired
    HireDao hireDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CopyDao copyDao;

    @Enumerated(EnumType.STRING)
    private StatusHireEnum statusHireEnum;

    @PostMapping("/user/commande")
    public ResponseEntity<Hire> createHire(@RequestBody Hire hire) {

        if (hire.getId() != null) {

            Optional<Hire> optional = hireDao.findById(hire.getId());

            if (optional.isPresent()) {
                Hire hireToUpdate = optional.get();
                hireToUpdate.setDateHire(hire.getDateHire());
                hireToUpdate.setDatePlannedReturn(hire.getDatePlannedReturn());
                hireToUpdate.setCopy(hire.getCopy());
                hireToUpdate.setUser(hire.getUser());
                hireToUpdate.setEventHire(hire.getEventHire());
                hireDao.save(hire);

                return new ResponseEntity<>(hire, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        hire.setStatus(statusHireEnum.PENDING.getValue()); // lorsque la location est créée, son statut est par défaut mis en "en attente" de valiation
        // par l'administrateur, celui-ci sera modifié lorsque l'administrateur décide d'accepter ou non la location.
        this.hireDao.save(hire);
        return new ResponseEntity<>(hire, HttpStatus.OK);
    }


    @GetMapping("/admin/hires")
    public List<Hire> getHires() {
        List hires = hireDao.findAll();
        return hires;
    }

    @GetMapping("/user/HireUser/{idUser}")
    public List<Hire> getHiresByUser(@PathVariable int idUser) {
        List hires = hireDao.findHireByIdUser(idUser);
        return hires;
    }

    @PostMapping("/admin/validate/hire")
    public ResponseEntity<Hire> changeStatusCopy(@RequestBody Hire hire) {


        if (hire.getId() != null) {

            Optional<Hire> optional = hireDao.findById(hire.getId());

            if (optional.isPresent()) {
                Hire hireToUpdate = optional.get();

                hireToUpdate.setDateHire(hire.getDateHire());
                hireToUpdate.setDatePlannedReturn(hire.getDatePlannedReturn());
                hireToUpdate.setDateRealReturn(hire.getDateRealReturn());
                hireToUpdate.setCopy(hire.getCopy());
                hireToUpdate.setEventHire(hire.getEventHire());
                hireToUpdate.setUser(hire.getUser());


                hireToUpdate.setStatus(statusHireEnum.ON_GOING.getValue());

                hireDao.save(hireToUpdate);


                return new ResponseEntity<>(hire, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        hireDao.save(hire);
        return new ResponseEntity<>(hire, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteHire/{id}")
    @JsonView({ViewHire.class})
    public ResponseEntity<Hire> deleteHire(@PathVariable int id){
        Optional<Hire> hireToDelete = hireDao.findById(id);
        if(hireToDelete.isPresent()){
            hireDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
