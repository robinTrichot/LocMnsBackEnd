package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewStructure;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
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

    @JsonView({ViewStructure.class, ViewUser.class})
    private String wording;

    private String picture;

    private int phone;

    private String type;

    private int streetNumber;

    private String nameStreet;

    private String postalCode;

    private String city;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "belongs_user_structure",
            joinColumns = @JoinColumn(name = "structure_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();
}
