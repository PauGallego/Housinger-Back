package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import net.paugallego.housinger.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MessageDTOConverter extends AbstractDTOConverter<MessageEntity, MessageDTO> {




    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public MessageEntity convertFromDTO(MessageDTO MessageDTO) {
        MessageEntity entity = new MessageEntity();

        entity.setMessage(MessageDTO.getMessage());
        entity.setDate(MessageDTO.getDate());
        entity.setStatus(MessageDTO.getStatus());
        entity.setReceiver(customerRepository.findById(MessageDTO.getReceiverId()).orElse(null));
        entity.setSender(customerRepository.findById(MessageDTO.getSenderId()).orElse(null));
        entity.setId(MessageDTO.getId());
        return entity;
    }

    @Override
    public MessageDTO convertFromEntity(MessageEntity entity) {
        MessageDTO dto = new MessageDTO();

        dto.setDate(entity.getDate());
        dto.setMessage(entity.getMessage());
        dto.setStatus(entity.getStatus());
        dto.setSenderId(entity.getSender().getId());
        dto.setReceiverId(entity.getReceiver().getId());
        dto.setId(entity.getId());
        dto.setReceiverName(entity.getReceiver().getName());
        dto.setReceiverSurname(entity.getReceiver().getSurname());
        dto.setReceiverPicture(entity.getReceiver().getPicture());
        dto.setSenderName(entity.getSender().getName());
        dto.setSenderSurname(entity.getSender().getSurname());
        dto.setSenderPicture(entity.getSender().getPicture());

        return dto;
    }
}
