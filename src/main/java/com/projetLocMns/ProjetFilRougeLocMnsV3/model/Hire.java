package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.*;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Hire {

    @JsonView(ViewHire.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(ViewHire.class)
    private LocalDate dateHire;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(ViewHire.class)
    private LocalDate dateRealReturn;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(ViewHire.class)
    private LocalDate datePlannedReturn;

    @JsonView(ViewHire.class)
    private String status;

    @JsonView(ViewHire.class)
    @ManyToOne
    @JoinColumn(name = "idLocation")
    private EventHire eventHire;

    @JsonView({ViewHire.class})
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "copy")
    private Copy copy;

}
