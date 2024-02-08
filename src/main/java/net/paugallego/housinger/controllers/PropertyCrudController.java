package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.entity.PropertyCRUDService;
import net.paugallego.housinger.services.crud.entity.ReservationCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/property")
public class PropertyCrudController extends AbstractController<PropertyEntity, PropertyDTO, PropertyCRUDService, PropertyDTOConverter, PropertyRepository, Long> {
}
