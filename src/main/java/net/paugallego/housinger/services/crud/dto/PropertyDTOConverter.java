package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.dto.PropertyDTO;
import org.springframework.stereotype.Service;

@Service
public class PropertyDTOConverter extends AbstractDTOConverter<PropertyEntity, PropertyDTO> {
    @Override
    public PropertyEntity convertFromDTO(PropertyDTO PropertyDTO) {
        PropertyEntity entity = new PropertyEntity();

        entity.setAddress(PropertyDTO.getAddress());
        entity.setDescription(PropertyDTO.getDescription());
        entity.setCalendar(PropertyDTO.getCalendar());
        entity.setFotos(PropertyDTO.getFotos());
        entity.setTypeId(PropertyDTO.getTypeId());
        entity.setCharacteristicsId(PropertyDTO.getCharacteristicsId());
        entity.setExtraInfo(PropertyDTO.getExtraInfo());
        entity.setUserId(PropertyDTO.getUserId());
        entity.setNormas(PropertyDTO.getNormas());
        entity.setId(PropertyDTO.getId());

        return entity;
    }

    @Override
    public PropertyDTO convertFromEntity(PropertyEntity entity) {
        PropertyDTO dto = new PropertyDTO();


        dto.setAddress(entity.getAddress());
        dto.setDescription(entity.getDescription());
        dto.setCalendar(entity.getCalendar());
        dto.setFotos(entity.getFotos());
        dto.setTypeId(entity.getTypeId());
        dto.setCharacteristicsId(entity.getCharacteristicsId());
        dto.setExtraInfo(entity.getExtraInfo());
        dto.setUserId(entity.getUserId());
        dto.setNormas(entity.getNormas());
        dto.setId(entity.getId());

        return dto;
    }
}
