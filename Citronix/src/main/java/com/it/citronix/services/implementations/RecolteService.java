package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Recolte.CreateRecolteDTO;
import com.it.citronix.models.dtos.Recolte.ResponseRecolteDTO;
import com.it.citronix.models.dtos.Recolte.UpdateRecolteDTO;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.mappers.RecolteMapper;
import com.it.citronix.repository.RecolteRepository;
import com.it.citronix.services.interfaces.IRecolteService;
import org.springframework.stereotype.Service;

@Service
public class RecolteService extends GenericService<Recolte, CreateRecolteDTO, UpdateRecolteDTO, ResponseRecolteDTO> implements IRecolteService {

    public RecolteService(RecolteRepository recolteRepository, RecolteMapper recolteMapper) {
        super(recolteRepository, recolteMapper);
    }

}
