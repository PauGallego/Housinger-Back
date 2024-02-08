package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.MessageRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.services.crud.dto.MessageDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.entity.MessageCRUDService;
import net.paugallego.housinger.services.crud.entity.PropertyCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/message")
public class MessageCrudController extends AbstractController<MessageEntity, MessageDTO, MessageCRUDService, MessageDTOConverter, MessageRepository, Long> {
}
