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
        entity.setUserReceive(MessageDTO.getUserReceive());
        entity.setUserSend(MessageDTO.getUserSend());
        entity.setId(MessageDTO.getId());

        return entity;
    }

    @Override
    public ChatDTO convertFromEntity(ChatEntity entity) {
        ChatDTO dto = new ChatDTO();


        dto.setMessage(entity.getMessage());
        dto.setDate(entity.getDate());
        dto.setUserReceive(entity.getUserReceive());
        dto.setUserSend(entity.getUserSend());
        dto.setId(entity.getId());

        return dto;
    }
}
