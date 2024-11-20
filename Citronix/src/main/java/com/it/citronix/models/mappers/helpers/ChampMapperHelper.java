package com.it.citronix.models.mappers.helpers;

import com.it.citronix.models.entities.Champ;
import com.it.citronix.repository.ChampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChampMapperHelper {

    @Autowired
    private ChampRepository champRepository;

    public Champ mapChampIdToChamp(Long champId) {
        return champRepository.findById(champId).orElse(null);
    }
}
