package com.it.citronix.models.mappers.helpers;

import com.it.citronix.models.entities.Ferme;
import com.it.citronix.repository.FermeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FermeMapperHelper {

    @Autowired
    private FermeRepository fermeRepository;

    public Ferme mapFermeIdToFerme(Long fermeId) {
        return fermeRepository.findById(fermeId).orElse(null);
    }
}
