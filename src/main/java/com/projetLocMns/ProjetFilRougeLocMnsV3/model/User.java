package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewUser.class)
    private Integer id;

    private String password;

    @JsonView(ViewUser.class)
    private String lastname;
    @JsonView(ViewUser.class)
    private String firstname;
    @JsonView(ViewUser.class)
    private int phone;
    @JsonView(ViewUser.class)
    private int cellPhone;
    @JsonView(ViewUser.class)
    private String mail;

    @JsonView(ViewUser.class)
    private String nomImageProfil;
    @JsonView(ViewUser.class)
    private int streetNumber;
    @JsonView(ViewUser.class)
    private String nameStreet;
    @JsonView(ViewUser.class)
    private String postalCode;
    @JsonView(ViewUser.class)
    private String city;

    @JsonView(ViewUser.class)
    @ManyToOne
    @JoinColumn(name = "role")
    private TypeUsager role;


    //    Les jointures OneToMany sont des relations faibles. C’est à dire qu’elles sont optionnelles, et qu’elles ne
//    peuvent exister sans une jointure ManyToOne déjà définies dans l’entité ciblée.
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Connection> connections;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "belongs_user_structure",
            inverseJoinColumns = @JoinColumn(name = "structure_id")
    )
    private Set<Structure> structures = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Hire> hires;
}
