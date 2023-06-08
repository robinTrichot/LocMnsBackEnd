package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.FailureDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Failure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FailureController {
    @Autowired
    FailureDao failureDao;

    @GetMapping("/failures")
    @JsonView({ViewFailure.class})
    public List<Failure> getFailures() {
        List failures = failureDao.findAll();
        return failures;
    }

    @GetMapping("/failure/{id}")
    @JsonView({ViewFailure.class})
    public ResponseEntity<Failure> getFailure(@PathVariable int id) {
        Optional<Failure> optionalFailure = failureDao.findById(id);
        if (optionalFailure.isPresent()) {
            return new ResponseEntity<>(optionalFailure.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("panne")
    public ResponseEntity<Failure> addFailure(@RequestBody Failure newFailure) {

        if (newFailure.getId() != null) {

            Optional<Failure> optional = failureDao.findById(newFailure.getId());

            if (optional.isPresent()) {

                Failure failureToUpdate = optional.get();
                failureToUpdate.setDateFailure(newFailure.getDateFailure());
                failureToUpdate.setDateSendRepair(newFailure.getDateSendRepair());
                failureToUpdate.setDateReturnRepair(newFailure.getDateReturnRepair());
                failureToUpdate.setNumberQuote(newFailure.getNumberQuote());
                failureToUpdate.setPriceRepair(newFailure.getPriceRepair());
                failureToUpdate.setDescriptionFailure(newFailure.getDescriptionFailure());
                failureToUpdate.setCopy(newFailure.getCopy());
                failureToUpdate.setUser(newFailure.getUser());
                failureToUpdate.setSubcontractor(newFailure.getSubcontractor());

                failureDao.save(newFailure);

                return new ResponseEntity<>(newFailure, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        failureDao.save(newFailure);
        return new ResponseEntity<>(newFailure, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteFailure/{id}")
    @JsonView({ViewFailure.class})
    public ResponseEntity<Failure> deleteFailure(@PathVariable int id){
        Optional<Failure> failureToDelete = failureDao.findById(id);
        if(failureToDelete.isPresent()){
            failureDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
