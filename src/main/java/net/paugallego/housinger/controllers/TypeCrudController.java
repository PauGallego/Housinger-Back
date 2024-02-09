package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.TypeEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.TypeRepository;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import net.paugallego.housinger.model.dto.TypeDTO;
import net.paugallego.housinger.services.crud.dto.CharacteristicDTOConverter;
import net.paugallego.housinger.services.crud.dto.TypeDTOConverter;
import net.paugallego.housinger.services.crud.entity.CharacteristicCRUDService;
import net.paugallego.housinger.services.crud.entity.TypeCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/type")
public class TypeCrudController extends AbstractController<TypeEntity, TypeDTO, TypeCRUDService, TypeDTOConverter, TypeRepository, Long> {
}
