package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.CharacteristicDTOConverter;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicCRUDService extends AbstractCRUDService<CharacteristicEntity, CharacteristicDTO, CharacteristicDTOConverter, CharacteristicRepository, Long> {
}
