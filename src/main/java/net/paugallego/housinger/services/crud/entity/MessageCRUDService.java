package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.ChatRepository;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.MessageDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyCharacteristicsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageCRUDService {


    @Autowired
    ChatRepository repository;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
   MessageDTOConverter dtoConverter;


    public List<MessageDTO> findByRecieverId(Long id){

        CustomerEntity customer = customerRepository.findById(id).orElse(null);

        List<MessageEntity> messages =  repository.findByReceiver(customer);

        List<MessageDTO> messageDTOS;


        return dtoConverter.convertFromEntities(messages);


    }

    public List<MessageDTO> findBySenderId(Long id){

        CustomerEntity customer = customerRepository.findById(id).orElse(null);

        List<MessageEntity> messages =  repository.findBySender(customer);

        List<MessageDTO> messageDTOS;


        return dtoConverter.convertFromEntities(messages);


    }

}


