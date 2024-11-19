package com.it.citronix.repository;

import com.it.citronix.models.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {
}
