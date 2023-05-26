package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.CopyDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
