package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.ChatEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatDTOConverter extends AbstractDTOConverter<ChatEntity, ChatDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ChatEntity convertFromDTO(ChatDTO messageDTO) {
        ChatEntity entity = new ChatEntity();

        entity.setMessage(messageDTO.getMessage());
        entity.setDate(messageDTO.getDate());

        UserEntity userReceive = userRepository.findById(messageDTO.getUserReceiveId()).orElse(null);
        entity.setUserReceive(userReceive);

        UserEntity userSend = userRepository.findById(messageDTO.getUserSendId()).orElse(null);
        entity.setUserSend(userSend);

        entity.setId(messageDTO.getId());

        return entity;
    }

    @Override
    public ChatDTO convertFromEntity(ChatEntity entity) {
        ChatDTO dto = new ChatDTO();

        dto.setMessage(entity.getMessage());
        dto.setDate(entity.getDate());

        // Asignar el ID del usuario que recibe el mensaje a la DTO
        if (entity.getUserReceive() != null) {
            dto.setUserReceiveId(entity.getUserReceive().getId());
        }

        // Asignar el ID del usuario que envía el mensaje a la DTO
        if (entity.getUserSend() != null) {
            dto.setUserSendId(entity.getUserSend().getId());
        }

        dto.setId(entity.getId());

        return dto;
    }
}

