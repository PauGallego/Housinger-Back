package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.repositories.BedRepository;
import net.paugallego.housinger.model.database.repositories.BedTypeRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import net.paugallego.housinger.services.crud.dto.BedDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedCRUDService extends AbstractCRUDService<BedEntity, BedDTO, BedDTOConverter, BedRepository, Long> {

    @Autowired
    private BedTypeRepository bedTypeRepository;

    @Autowired
    private BedDTOConverter bedDTOConverter;

    @Autowired
    private BedRepository bedRepository;



}
