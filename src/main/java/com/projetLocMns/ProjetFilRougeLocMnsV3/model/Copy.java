package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.*;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewCopy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter

public class Copy {

    @JsonView(ViewCopy.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewCopy.class)
    private LocalDate datePurchase;

    @JsonView(ViewCopy.class)
    private String status;


    @JsonView(ViewCopy.class)
    private String outOfStock;


    @JsonView(ViewCopy.class)
    private LocalDate dateOutOfStock;

    @JsonView(ViewCopy.class)
    private String serialNumber;


    @JsonView({ViewCopy.class, ViewHire.class})

    @ManyToOne
    @JoinColumn(name = "material")
    private Material material;



    @ManyToOne
    @JoinColumn(name = "features")
    private Features features;


//    @OneToMany(mappedBy = "copy")
//    private Set<Hire> hires;

}
