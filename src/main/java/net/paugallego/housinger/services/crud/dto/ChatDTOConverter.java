package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.ChatEntity;
import net.paugallego.housinger.model.dto.ChatDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatDTOConverter extends AbstractDTOConverter<ChatEntity, ChatDTO> {
    @Override
    public ChatEntity convertFromDTO(ChatDTO MessageDTO) {
        ChatEntity entity = new ChatEntity();

        entity.setMessage(MessageDTO.getMessage());
        entity.setDate(MessageDTO.getDate());
        entity.setUserReceiveId(MessageDTO.getUserReceiveId());
        entity.setUserSendId(MessageDTO.getUserSendId());
        entity.setId(MessageDTO.getId());

        return entity;
    }

    @Override
    public ChatDTO convertFromEntity(ChatEntity entity) {
        ChatDTO dto = new ChatDTO();


        dto.setMessage(entity.getMessage());
        dto.setDate(entity.getDate());
        dto.setUserReceiveId(entity.getUserReceiveId());
        dto.setUserSendId(entity.getUserSendId());
        dto.setId(entity.getId());

        return dto;
    }
}
