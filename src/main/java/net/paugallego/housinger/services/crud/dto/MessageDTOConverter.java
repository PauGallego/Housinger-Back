package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.dto.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public class MessageDTOConverter extends AbstractDTOConverter<MessageEntity, MessageDTO> {
    @Override
    public MessageEntity convertFromDTO(MessageDTO MessageDTO) {
        MessageEntity entity = new MessageEntity();

        entity.setMessage(MessageDTO.getMessage());
        entity.setDate(MessageDTO.getDate());
        entity.setUserReceiveId(MessageDTO.getUserReceiveId());
        entity.setUserSendId(MessageDTO.getUserSendId());
        entity.setId(MessageDTO.getId());

        return entity;
    }

    @Override
    public MessageDTO convertFromEntity(MessageEntity entity) {
        MessageDTO dto = new MessageDTO();


        dto.setMessage(entity.getMessage());
        dto.setDate(entity.getDate());
        dto.setUserReceiveId(entity.getUserReceiveId());
        dto.setUserSendId(entity.getUserSendId());
        dto.setId(entity.getId());

        return dto;
    }
}
