package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.models.mappers.FermeMapper;
import com.it.citronix.repository.FermeRepository;
import com.it.citronix.services.interfaces.IFermeService;
import org.springframework.stereotype.Service;

@Service
public class FermeService extends GenericService<Ferme, CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> implements IFermeService {

    public FermeService(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        super(fermeRepository, fermeMapper);
    }

}
