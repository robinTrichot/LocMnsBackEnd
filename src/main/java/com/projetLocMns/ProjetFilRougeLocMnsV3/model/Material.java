package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.*;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
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

    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "trademark")
    private TrademarkMaterial trademarkMaterial;

    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "structure")
    private Structure structure;


// ça merde ici, à changer
    // àa conftionne avec un jsonBack reference

    @JsonIgnore
   // @JsonView(ViewMaterial.class)
    @OneToMany( mappedBy = "material")
    private List<Copy> copies = new ArrayList<>();
}
