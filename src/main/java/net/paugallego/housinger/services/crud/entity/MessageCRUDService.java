package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.MessageRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.services.crud.dto.MessageDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class MessageCRUDService extends AbstractCRUDService<MessageEntity, MessageDTO, MessageDTOConverter, MessageRepository, Long> {
}
