package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.repositories.BedRepository;
import net.paugallego.housinger.model.database.repositories.BedTypeRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import net.paugallego.housinger.services.crud.dto.BedDTOConverter;
import net.paugallego.housinger.services.crud.dto.BedTypeDTOConverter;
import net.paugallego.housinger.services.crud.entity.BedCRUDService;
import net.paugallego.housinger.services.crud.entity.BedTypeCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/bedType")
public class BedTypeCrudController extends AbstractController<BedTypeEntity, BedTypeDTO, BedTypeCRUDService, BedTypeDTOConverter, BedTypeRepository, Long> {
}
