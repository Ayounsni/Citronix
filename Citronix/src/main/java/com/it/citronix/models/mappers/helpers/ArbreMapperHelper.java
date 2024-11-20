package com.it.citronix.models.mappers.helpers;

import com.it.citronix.models.entities.Arbre;
import com.it.citronix.repository.ArbreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArbreMapperHelper {

    @Autowired
    private ArbreRepository arbreRepository;

    public Arbre mapArbreIdToArbre(Long arbreId) {
        return arbreRepository.findById(arbreId).orElse(null);
    }
}
