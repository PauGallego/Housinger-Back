package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.ChatEntity;
import net.paugallego.housinger.model.database.repositories.ChatRepository;
import net.paugallego.housinger.model.dto.ChatDTO;
import net.paugallego.housinger.services.crud.dto.ChatDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ChatCRUDService extends AbstractCRUDService<ChatEntity, ChatDTO, ChatDTOConverter, ChatRepository, Long> {
}
