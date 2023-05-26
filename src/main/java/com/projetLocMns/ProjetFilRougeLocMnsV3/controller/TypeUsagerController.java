package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.TypeUsagerDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

}
