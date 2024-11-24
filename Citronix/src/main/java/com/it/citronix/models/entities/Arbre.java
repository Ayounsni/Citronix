package com.it.citronix.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Champ champ;

    @NotNull
    private LocalDate datePlantation;

    @OneToMany(mappedBy = "arbre",cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)
    private List<RecolteDetail> recolteDetails;

    public String calculerAge() {
        Period period = Period.between(datePlantation, LocalDate.now());
        int years = period.getYears();
        int months = period.getMonths();

        return years + " an(s) et " + months + " mois";
    }

    public double calculerProductiviteAnnuel() {
        int age = Period.between(datePlantation, LocalDate.now()).getYears();

        if (age < 3) {
            return 2.5 * 4;
        } else if (age <= 10) {
            return 12.0 * 4;
        } else if (age <= 20) {
            return 20.0 * 4;
        } else {
            return 0.0;
        }
    }
}
