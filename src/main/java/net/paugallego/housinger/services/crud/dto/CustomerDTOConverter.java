package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import net.paugallego.housinger.model.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDTOConverter extends AbstractDTOConverter<CustomerEntity, CustomerDTO> {

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomerEntity convertFromDTO(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUserEntity(userRepository.findById(dto.getUserEntityId()).orElse(null));
        entity.setDni(dto.getDni());
        entity.setSurname(dto.getSurname());
        entity.setPicture(dto.getPicture());


        return entity;
    }

    @Override
    public CustomerDTO convertFromEntity(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setDni(dto.getDni());
        dto.setUserEntityId(entity.getUserEntity().getId());
        dto.setSurname(entity.getSurname());
        dto.setId(dto.getId());
        dto.setPicture(entity.getPicture());
        dto.setName(entity.getName());

        return dto;
    }
}
