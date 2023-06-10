package com.projetLocMns.ProjetFilRougeLocMnsV3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.dao.EventHireDao;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.EventHire;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewEventHire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class EventHireController {

    @Autowired
    EventHireDao eventHireDao;

    @GetMapping("/user/EventHire")
    public List<EventHire> getEventHire() {
        List eventHires = eventHireDao.findAll();
        return eventHires;
    }

    @GetMapping("/user/eventHire/{id}")
    @JsonView({ViewEventHire.class})
    public ResponseEntity<EventHire> getEventHire(@PathVariable int id) {
        Optional<EventHire> optionalEventHire = eventHireDao.findById(id);
        if (optionalEventHire.isPresent()) {
            return new ResponseEntity<>(optionalEventHire.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/addEventHire")
    public ResponseEntity<EventHire> addEventHire(
            @RequestBody EventHire newEventHire
    ) {
        if (newEventHire.getId() != null) {
            Optional<EventHire> optionalEventHire = eventHireDao.findById(newEventHire.getId());
            if (optionalEventHire.isPresent())  {
                EventHire eventHireToUpdate = optionalEventHire.get();
                eventHireToUpdate.setNameEvent(newEventHire.getNameEvent());

                eventHireDao.save(eventHireToUpdate);

                return new ResponseEntity<>(newEventHire, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        eventHireDao.save(newEventHire);
        return new ResponseEntity<>(newEventHire, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteEventHire/{id}")
    @JsonView({ViewEventHire.class})
    public ResponseEntity<EventHire> deleteEventHire(@PathVariable int id){
        Optional<EventHire> eventHireToDelete = eventHireDao.findById(id);
        if(eventHireToDelete.isPresent()){
            eventHireDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
