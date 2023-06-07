package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.*;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewMaterial;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonView(ViewMaterial.class)
    private String notice;

    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "trademark")
    private TrademarkMaterial trademarkMaterial;

    @JsonView(ViewMaterial.class)
    @ManyToOne
    @JoinColumn(name = "structure")
    private Structure structure;

    @JsonIgnore
    @OneToMany( mappedBy = "material")
    private List<Copy> copies = new ArrayList<>();
}
