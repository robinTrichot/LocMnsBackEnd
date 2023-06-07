package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Failure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateFailure;

    private LocalDate dateSendRepair;

    private LocalDate dateReturnRepair;

    private int numberQuote;

    private double priceRepair;

    private String descriptionFailure;

    @ManyToOne
    @JoinColumn(name = "copy")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "usager")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subcontractor")
    private Subcontractor subcontractor;
}
