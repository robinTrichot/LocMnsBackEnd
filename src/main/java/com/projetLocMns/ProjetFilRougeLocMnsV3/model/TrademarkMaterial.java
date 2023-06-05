package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewMaterial;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TrademarkMaterial {

    @JsonView(ViewMaterial.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewMaterial.class)
    private String wording;


}
