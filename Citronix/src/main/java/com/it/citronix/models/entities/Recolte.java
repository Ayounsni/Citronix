package com.it.citronix.models.entities;

import com.it.citronix.models.enums.Saison;
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
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Saison saison;

    @NotNull
    private LocalDate dateRecolte;

    @NotNull
    private Double quantiteTotal = 0.00;

    @NotNull
    private Double quantiteRestante = 0.00;

    @OneToMany(mappedBy = "recolte",cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)
    private List<RecolteDetail> recolteDetails;

    @OneToMany(mappedBy = "recolte",cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)
    private List<Vente> ventes;
}
