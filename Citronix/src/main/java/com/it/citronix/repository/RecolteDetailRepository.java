package com.it.citronix.repository;

import com.it.citronix.models.embeddableId.RecolteDetailId;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.entities.RecolteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, RecolteDetailId> {

    List<RecolteDetail> findByArbreId(Long arbre_id);

}
