package com.it.citronix.repository;

import com.it.citronix.models.entities.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {

    Long countByChampId(Integer fermeId);
}
