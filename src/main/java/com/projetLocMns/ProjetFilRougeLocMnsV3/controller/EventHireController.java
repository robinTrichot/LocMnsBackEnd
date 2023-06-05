package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.EventHireDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.EventHire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class EventHireController {

    @Autowired
    EventHireDao eventHireDao;

    @GetMapping("/EventHire")
    public List<EventHire> getEventHire() {
        List eventHires = eventHireDao.findAll();
        return eventHires;
    }
}
