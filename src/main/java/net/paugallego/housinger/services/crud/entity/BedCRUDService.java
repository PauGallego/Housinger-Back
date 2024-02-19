package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.repositories.BedRepository;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.services.crud.dto.BedDTOConverter;
import net.paugallego.housinger.services.crud.dto.CalendarDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class BedCRUDService extends AbstractCRUDService<BedEntity, BedDTO, BedDTOConverter, BedRepository, Long> {
}
