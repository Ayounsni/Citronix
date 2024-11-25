package com.it.citronix.repository.Specifications;

import com.it.citronix.models.entities.Ferme;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class FermeSpecification {

    public static Specification<Ferme> searchFermes(String nom, String localisation) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nom != null && !nom.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("nom"), "%" + nom + "%"));
            }

            if (localisation != null && !localisation.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("localisation"), localisation));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
