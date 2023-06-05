package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewMaterial;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Structure {

    @JsonView(ViewMaterial.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewMaterial.class)
    private String wording;

    @JsonView(ViewMaterial.class)
    private String picture;

    @JsonView(ViewMaterial.class)
    private int phone;

    @JsonView(ViewMaterial.class)
    private String type;

    @JsonView(ViewMaterial.class)
    private int streetNumber;

    @JsonView(ViewMaterial.class)
    private String nameStreet;

    @JsonView(ViewMaterial.class)
    private String postalCode;

    @JsonView(ViewMaterial.class)
    private String city;
}
