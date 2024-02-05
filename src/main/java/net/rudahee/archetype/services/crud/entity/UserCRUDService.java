package net.rudahee.archetype.services.crud.entity;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.entities.UserEntity;
import net.rudahee.archetype.model.database.repositories.ExampleRepository;
import net.rudahee.archetype.model.database.repositories.UserRepository;
import net.rudahee.archetype.model.dto.ExampleDTO;
import net.rudahee.archetype.model.dto.UserDTO;
import net.rudahee.archetype.services.crud.dto.ExampleDTOConverter;
import net.rudahee.archetype.services.crud.dto.UserDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class UserCRUDService extends AbstractCRUDService<UserEntity, UserDTO, UserDTOConverter, UserRepository, Long> {
}
