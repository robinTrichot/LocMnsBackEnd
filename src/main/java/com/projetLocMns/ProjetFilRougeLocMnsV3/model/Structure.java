package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String wording;

    private String picture;

    private int phone;

    private String type;

    private int streetNumber;

    private String nameStreet;

    private String postalCode;


    private String city;
}
