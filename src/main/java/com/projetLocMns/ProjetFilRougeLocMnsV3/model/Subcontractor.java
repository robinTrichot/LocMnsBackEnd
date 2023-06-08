package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewStructure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewSubcontractor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Subcontractor {

    @JsonView(ViewSubcontractor.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewSubcontractor.class)
    private String designation;

    @JsonView(ViewSubcontractor.class)
    private String phone;

    @JsonView(ViewSubcontractor.class)
    private int numberStreet;

    @JsonView(ViewSubcontractor.class)
    private String nameStreet;

    @JsonView(ViewSubcontractor.class)
    private String postaleCode;

    @JsonView(ViewSubcontractor.class)
    private String city;

}
