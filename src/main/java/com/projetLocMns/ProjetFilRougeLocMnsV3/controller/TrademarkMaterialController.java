package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.TrademarkMaterialDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.TrademarkMaterial;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewTrademarkMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TrademarkMaterialController {
    @Autowired
    TrademarkMaterialDao trademarkMaterialDao;

    @GetMapping("/trademarksMaterial")
    @JsonView({ViewTrademarkMaterial.class})
    public List<TrademarkMaterial> getTrademarksMaterial() {
        List trademarksMaterial = trademarkMaterialDao.findAll();
        return trademarksMaterial;
    }

    @GetMapping("/trademarkMaterial/{id}")
    @JsonView({ViewTrademarkMaterial.class})
    public ResponseEntity<TrademarkMaterial> getTrademarkMaterial(@PathVariable int id) {
        Optional<TrademarkMaterial> optionalTrademarkMaterial = trademarkMaterialDao.findById(id);
        if (optionalTrademarkMaterial.isPresent()) {
            return new ResponseEntity<>(optionalTrademarkMaterial.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addTrademarkMaterial")
    public ResponseEntity<TrademarkMaterial> addTrademarkMaterial(@RequestBody TrademarkMaterial newTrademarkMaterial) {

        if (newTrademarkMaterial.getId() != null) {

            Optional<TrademarkMaterial> optional = trademarkMaterialDao.findById(newTrademarkMaterial.getId());

            if (optional.isPresent()) {

                TrademarkMaterial trademarkMaterialToUpdate = optional.get();
                trademarkMaterialToUpdate.setWording(newTrademarkMaterial.getWording());


                trademarkMaterialDao.save(newTrademarkMaterial);

                return new ResponseEntity<>(newTrademarkMaterial, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        trademarkMaterialDao.save(newTrademarkMaterial);
        return new ResponseEntity<>(newTrademarkMaterial, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteTrademarkMaterial/{id}")
    @JsonView({ViewTrademarkMaterial.class})
    public ResponseEntity<TrademarkMaterial> deleteTrademarkMaterial(@PathVariable int id){
        Optional<TrademarkMaterial> trademarkMaterialToDelete = trademarkMaterialDao.findById(id);
        if(trademarkMaterialToDelete.isPresent()){
            trademarkMaterialDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
