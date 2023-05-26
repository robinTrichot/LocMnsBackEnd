package com.projetLocMns.ProjetFilRougeLocMnsV3.model;


import com.fasterxml.jackson.annotation.*;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewHire;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Hire implements Serializable {

    @JsonView(ViewHire.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ViewHire.class)
    private LocalDate dateHire;

    @JsonView(ViewHire.class)
    private LocalDate dateRealReturn;

    @JsonView(ViewHire.class)
    private LocalDate datePlannedReturn;

    @JsonView(ViewHire.class)
    private String status;
    @JsonView(ViewHire.class)
    @ManyToOne
    @JoinColumn(name = "idLocation")
    private EventHire eventHire;

    @JsonView(ViewHire.class)
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;



    @JsonView(ViewHire.class)
    @ManyToOne
    @JoinColumn(name = "idCopy")
    private Copy copy;

}
