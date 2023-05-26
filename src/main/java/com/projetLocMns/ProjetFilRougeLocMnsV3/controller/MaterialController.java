package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.MaterialDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MaterialController {

    @Autowired
    MaterialDao materialDao;

    @GetMapping("/materials")
    public List<Material> getCopy() {
        List materials= materialDao.findAll();
        return materials;
    }



}
