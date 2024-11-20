package com.it.citronix.repository;

import com.it.citronix.models.embeddableId.RecolteDetailId;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.entities.RecolteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, RecolteDetailId> {
}
