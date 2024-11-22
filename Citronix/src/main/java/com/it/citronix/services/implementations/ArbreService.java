package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.mappers.ArbreMapper;
import com.it.citronix.repository.ArbreRepository;
import com.it.citronix.repository.ChampRepository;
import com.it.citronix.services.interfaces.IArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArbreService extends GenericService<Arbre, CreateArbreDTO, UpdateArbreDTO, ResponseArbreDTO> implements IArbreService {

    @Autowired
    ArbreRepository arbreRepository;

    @Autowired
    ArbreMapper arbreMapper;

    @Autowired
    ChampRepository champRepository;

    public ArbreService(ArbreRepository arbreRepository, ArbreMapper arbreMapper) {
        super(arbreRepository, arbreMapper);
    }

    @Override
    public ResponseArbreDTO create(CreateArbreDTO createArbreDTO){
        Champ champ = champRepository.findById(createArbreDTO.getChampId()).orElseThrow();

        Long nombreArbresExistants = arbreRepository.countByChampId(champ.getId());
        double superficieChamp = champ.getSuperficie();
        double densiteActuelle = (nombreArbresExistants + 1) / superficieChamp;

        double densiteMaximale = 10.0 / 1000.0;
        if (densiteActuelle > densiteMaximale) {
            throw new IllegalStateException("La densité maximale d'arbres pour ce champ est dépassée.  Le maximum autorisé pour ce champ est de " + superficieChamp + " arbres.");
        }

        return ;
    }

}
