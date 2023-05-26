package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewMaterial;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Material {

    @JsonView(ViewMaterial.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @JsonView({ViewCopy.class, ViewMaterial.class})
    private String wording;

    @JsonView(ViewMaterial.class)
    private String picture;


    //jSonIgnore ajouté pour afficher la requete dans la copycontroller : trouverExemplairesParMateriel()
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "owns_material_notice",
            inverseJoinColumns = @JoinColumn(name = "notice_id")
    )
    private Set<Notice> notices = new HashSet<>();

    // cette mofidication = changement de la features vers la table copy en liaison (à voir bansept)
//    @ManyToOne
//    @JoinColumn(name = "features")
//    private Features features;


    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "trademark")
    private TrademarkMaterial trademarkMaterial;


    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "structure")
    private Structure structure;



    @JsonView(ViewMaterial.class)
    @OneToMany(mappedBy = "material")
    private List<Copy> copies = new ArrayList<>();
}
