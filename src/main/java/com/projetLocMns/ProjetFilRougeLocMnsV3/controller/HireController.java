package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.CopyDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.HireDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.UserDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Hire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/commande")
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



        hire.setStatus("en attente"); // lorsque la location est créée, son statut est par défaut mis en "en attente" de valiation
        // par l'administrateur, celui-ci sera modifié lorsque l'administrateur décide d'accepter ou non la location.
        this.hireDao.save(hire);
        return new ResponseEntity<>(hire, HttpStatus.OK);
    }


    @GetMapping("/admin/hires")
    public List<Hire> getHires() {
        List hires = hireDao.findAll();
        return hires;
    }

    @GetMapping("/HireUser/{idUser}")
    public List<Hire> getHiresByUser(@PathVariable int idUser) {
        List hires = hireDao.findHireByIdUser(idUser);
        return hires;
    }

}
