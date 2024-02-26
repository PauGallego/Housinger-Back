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
        entity.setType(PropertyDTO.getType());
        entity.setCharacteristics(PropertyDTO.getCharacteristics());
        entity.setExtraInfo(PropertyDTO.getExtraInfo());
        entity.setUser(PropertyDTO.getUser());
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
        dto.setType(entity.getType());
        dto.setCharacteristics(entity.getCharacteristics());
        dto.setExtraInfo(entity.getExtraInfo());
        dto.setUser(entity.getUser());
        dto.setNormas(entity.getNormas());
        dto.setId(entity.getId());;

        return dto;
    }
}
