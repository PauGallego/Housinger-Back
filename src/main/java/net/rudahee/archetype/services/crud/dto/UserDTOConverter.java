package net.rudahee.archetype.services.crud.dto;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.entities.UserEntity;
import net.rudahee.archetype.model.dto.ExampleDTO;
import net.rudahee.archetype.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDTOConverter extends AbstractDTOConverter<UserEntity, UserDTO> {
    @Override
    public UserEntity convertFromDTO(UserDTO userDTO) {
        UserEntity entity = new UserEntity();

        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setMail(userDTO.getMail());
        entity.setUsername(userDTO.getUsername());
        entity.setFoto(userDTO.getFoto());

        return entity;
    }

    @Override
    public UserDTO convertFromEntity(UserEntity entity) {
        UserDTO dto = new UserDTO();

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMail(entity.getMail());
        dto.setUsername(entity.getUsername());
        dto.setFoto(entity.getFoto());


        return dto;
    }
}
