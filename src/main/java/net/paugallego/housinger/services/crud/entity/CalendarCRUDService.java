package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.services.crud.dto.CalendarDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class CalendarCRUDService extends AbstractCRUDService<CalendarEntity, CalendarDTO, CalendarDTOConverter, CalendarRepository, Long> {
}
