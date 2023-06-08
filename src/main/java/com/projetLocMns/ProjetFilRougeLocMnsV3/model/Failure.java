package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewFailure;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Failure {

    @JsonView({ViewFailure.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView({ViewFailure.class})
    private LocalDate dateFailure;

    @JsonView({ViewFailure.class})
    private LocalDate dateSendRepair;

    @JsonView({ViewFailure.class})
    private LocalDate dateReturnRepair;

    @JsonView({ViewFailure.class})
    private int numberQuote;

    @JsonView({ViewFailure.class})
    private double priceRepair;

    @JsonView({ViewFailure.class})
    private String descriptionFailure;

    @JsonView({ViewFailure.class})
    @ManyToOne
    @JoinColumn(name = "copy")
    private Copy copy;

    @JsonView({ViewFailure.class})
    @ManyToOne
    @JoinColumn(name = "usager")
    private User user;

    @JsonView({ViewFailure.class})
    @ManyToOne
    @JoinColumn(name = "subcontractor")
    private Subcontractor subcontractor;
}
