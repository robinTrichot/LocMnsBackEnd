package com.projetLocMns.ProjetFilRougeLocMnsV3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.projetLocMns.ProjetFilRougeLocMnsV3.view.ViewUser;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Connection {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    private boolean status;

    private LocalDate dateConnection;

    private LocalDate dateLogout;


    @ManyToOne
    @JsonView(ViewUser.class)
    @JoinColumn(name = "idUser")
    private User user;


}
