package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewFeatures;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Features {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewCopy.class)
    private String wording;


    @JsonView(ViewFeatures.class)
    @OneToMany(mappedBy = "features")
    private List<Copy> copies = new ArrayList<>();

}
