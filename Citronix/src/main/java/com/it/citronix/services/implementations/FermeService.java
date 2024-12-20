package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.SearchFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.models.mappers.FermeMapper;
import com.it.citronix.repository.FermeRepository;
import com.it.citronix.repository.Specifications.FermeSpecification;
import com.it.citronix.services.interfaces.IFermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FermeService extends GenericService<Ferme, CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> implements IFermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private FermeMapper fermeMapper;

    public FermeService(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        super(fermeRepository, fermeMapper);
    }

    @Override
    public List<ResponseFermeDTO> rechercherFermes(String nom, String localisation) {
        List<Ferme> fermes = fermeRepository.findAll(FermeSpecification.searchFermes(nom, localisation));

        return fermes.stream()
                .map(fermeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
