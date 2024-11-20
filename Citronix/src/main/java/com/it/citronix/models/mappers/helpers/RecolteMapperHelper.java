package com.it.citronix.models.mappers.helpers;

import com.it.citronix.models.entities.Recolte;
import com.it.citronix.repository.RecolteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecolteMapperHelper {

    @Autowired
    private RecolteRepository recolteRepository;

    public Recolte mapRecolteIdToRecolte(Long recolteId) {
        return recolteRepository.findById(recolteId).orElse(null);
    }
}
