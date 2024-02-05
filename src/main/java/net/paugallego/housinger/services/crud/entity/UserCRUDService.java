package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserCRUDService extends AbstractCRUDService<UserEntity, UserDTO, UserDTOConverter, UserRepository, Long> {
}
