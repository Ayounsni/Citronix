package com.it.citronix.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Champ champ;

    @NotNull
    private LocalDate datePlantation;

    @OneToMany(mappedBy = "arbre",cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)
    private List<RecolteDetail> recolteDetails;
}
