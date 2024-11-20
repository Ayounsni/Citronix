package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Champ.CreateChampDTO;
import com.it.citronix.models.dtos.Champ.ResponseChampDTO;
import com.it.citronix.models.dtos.Champ.UpdateChampDTO;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.mappers.ChampMapper;
import com.it.citronix.repository.ChampRepository;
import com.it.citronix.services.interfaces.IChampService;
import org.springframework.stereotype.Service;

@Service
public class ChampService extends GenericService<Champ, CreateChampDTO, UpdateChampDTO, ResponseChampDTO> implements IChampService {

    public ChampService(ChampRepository champRepository, ChampMapper champMapper) {
        super(champRepository, champMapper);
    }

}
