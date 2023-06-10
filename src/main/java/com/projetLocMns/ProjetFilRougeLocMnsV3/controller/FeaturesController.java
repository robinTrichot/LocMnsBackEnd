package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.FeaturesDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Features;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FeaturesController {

    @Autowired
    FeaturesDao featuresDao;

    @GetMapping("/user/features")
    public List<Features> getFeatures() {
        List features = featuresDao.findAll();
        return features;
    }

    @GetMapping("/user/feature/{id}")
    @JsonView({ViewFeatures.class})
    public ResponseEntity<Features> getFeature(@PathVariable int id) {
        Optional<Features> optionalFeatures = featuresDao.findById(id);
        if (optionalFeatures.isPresent()) {
            return new ResponseEntity<>(optionalFeatures.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addFeature")
    public ResponseEntity<Features> addFeature(
            @RequestBody Features newFeature
    ) {
        if (newFeature.getId() != null) {
            Optional<Features> optionalFeatures = featuresDao.findById(newFeature.getId());
            if (optionalFeatures.isPresent())  {
                Features featureToUpdate = optionalFeatures.get();
                featureToUpdate.setWording(newFeature.getWording());

                featuresDao.save(featureToUpdate);

                return new ResponseEntity<>(newFeature, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        featuresDao.save(newFeature);
        return new ResponseEntity<>(newFeature, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteFeature/{id}")
    @JsonView({ViewFeatures.class})
    public ResponseEntity<Features> deleteFeature(@PathVariable int id){
        Optional<Features> featureToDelete = featuresDao.findById(id);
        if(featureToDelete.isPresent()){
            featuresDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

