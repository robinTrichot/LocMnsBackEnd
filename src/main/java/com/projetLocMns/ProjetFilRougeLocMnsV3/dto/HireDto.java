package com.projetLocMns.ProjetFilRougeLocMnsV3.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.EventHire;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Hire;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;




public class HireDto {


    private Integer id;
    private LocalDate dateHire;
    private LocalDate dateRealReturn;
    private LocalDate datePlannedReturn;
    private String status;


    // les records : les classes non modifiables;
    // pas d'annotations
    // pas d'id
    // juste les atributs à representer


    private EventHire eventHire;


    private User user;


    private Copy copy;



}
