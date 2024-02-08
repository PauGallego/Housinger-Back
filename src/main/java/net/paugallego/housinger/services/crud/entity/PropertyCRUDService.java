package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class PropertyCRUDService extends AbstractCRUDService<PropertyEntity, PropertyDTO, PropertyDTOConverter, PropertyRepository, Long> {
}
