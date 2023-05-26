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
public class Subcontractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String designation;
    private String phone;
    private int numberStreet;
    private String nameStreet;
    private String postaleCode;
    private String city;

}
