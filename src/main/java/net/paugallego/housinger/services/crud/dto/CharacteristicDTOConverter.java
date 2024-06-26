package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicDTOConverter extends AbstractDTOConverter<CharacteristicEntity, CharacteristicDTO> {
    @Override
    public CharacteristicEntity convertFromDTO(CharacteristicDTO CharacteristicDTO) {
        CharacteristicEntity entity = new CharacteristicEntity();

        entity.setName(CharacteristicDTO.getName());
        entity.setIcon(CharacteristicDTO.getIcon());
        entity.setId(CharacteristicDTO.getId());
        entity.setGrupo(CharacteristicDTO.getGrupo());

        return entity;
    }

    @Override
    public CharacteristicDTO convertFromEntity(CharacteristicEntity entity) {
        CharacteristicDTO dto = new CharacteristicDTO();

        dto.setName(entity.getName());
        dto.setIcon(entity.getIcon());
        dto.setId(entity.getId());
        dto.setGrupo(entity.getGrupo());


        return dto;
    }
}
