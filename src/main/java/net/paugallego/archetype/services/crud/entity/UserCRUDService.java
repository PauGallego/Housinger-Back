package net.paugallego.archetype.services.crud.entity;

import net.paugallego.archetype.services.crud.dto.UserDTOConverter;
import net.paugallego.archetype.model.database.entities.UserEntity;
import net.paugallego.archetype.model.database.repositories.UserRepository;
import net.paugallego.archetype.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserCRUDService extends AbstractCRUDService<UserEntity, UserDTO, UserDTOConverter, UserRepository, Long> {
}
