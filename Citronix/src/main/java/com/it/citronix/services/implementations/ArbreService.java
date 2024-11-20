package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.mappers.ArbreMapper;
import com.it.citronix.repository.ArbreRepository;
import com.it.citronix.services.interfaces.IArbreService;
import org.springframework.stereotype.Service;

@Service
public class ArbreService extends GenericService<Arbre, CreateArbreDTO, UpdateArbreDTO, ResponseArbreDTO> implements IArbreService {

    public ArbreService(ArbreRepository arbreRepository, ArbreMapper arbreMapper) {
        super(arbreRepository, arbreMapper);
    }

}
