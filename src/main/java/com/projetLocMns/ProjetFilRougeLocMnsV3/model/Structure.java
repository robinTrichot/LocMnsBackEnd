package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewStructure;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Structure {
    @JsonView(ViewStructure.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewStructure.class)
    private String wording;

    @JsonView(ViewStructure.class)
    private String picture;

    @JsonView(ViewStructure.class)
    private int phone;

    @JsonView(ViewStructure.class)
    private String type;

    @JsonView(ViewStructure.class)
    private int streetNumber;

    @JsonView(ViewStructure.class)
    private String nameStreet;

    @JsonView(ViewStructure.class)
    private String postalCode;

    @JsonView(ViewStructure.class)
    private String city;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "belongs_user_structure",
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();
}
