package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.MessageRepository;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.services.crud.dto.CharacteristicDTOConverter;
import net.paugallego.housinger.services.crud.dto.MessageDTOConverter;
import net.paugallego.housinger.services.crud.entity.CharacteristicCRUDService;
import net.paugallego.housinger.services.crud.entity.MessageCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/characteristic")
public class CharacteristicCrudController extends AbstractController<CharacteristicEntity, CharacteristicDTO, CharacteristicCRUDService, CharacteristicDTOConverter, CharacteristicRepository, Long> {
}
