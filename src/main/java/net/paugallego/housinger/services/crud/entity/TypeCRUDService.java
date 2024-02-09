package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.TypeEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.TypeRepository;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import net.paugallego.housinger.model.dto.TypeDTO;
import net.paugallego.housinger.services.crud.dto.CharacteristicDTOConverter;
import net.paugallego.housinger.services.crud.dto.TypeDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class TypeCRUDService extends AbstractCRUDService<TypeEntity, TypeDTO, TypeDTOConverter, TypeRepository, Long> {
}
