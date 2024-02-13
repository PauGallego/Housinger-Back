package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.ChatEntity;
import net.paugallego.housinger.model.database.repositories.ChatRepository;
import net.paugallego.housinger.model.dto.ChatDTO;
import net.paugallego.housinger.services.crud.dto.ChatDTOConverter;
import net.paugallego.housinger.services.crud.entity.ChatCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/message")
public class ChatCrudController extends AbstractController<ChatEntity, ChatDTO, ChatCRUDService, ChatDTOConverter, ChatRepository, Long> {
}
