package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.repositories.BedRepository;
import net.paugallego.housinger.model.database.repositories.BedTypeRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import net.paugallego.housinger.services.crud.dto.BedDTOConverter;
import net.paugallego.housinger.services.crud.dto.BedTypeDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class BedTypeCRUDService extends AbstractCRUDService<BedTypeEntity, BedTypeDTO, BedTypeDTOConverter, BedTypeRepository, Long> {
}
