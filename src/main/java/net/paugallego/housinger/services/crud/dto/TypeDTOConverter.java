package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.TypeEntity;
import net.paugallego.housinger.model.dto.TypeDTO;
import org.springframework.stereotype.Service;

@Service
public class TypeDTOConverter extends AbstractDTOConverter<TypeEntity, TypeDTO> {
    @Override
    public TypeEntity convertFromDTO(TypeDTO TypeDTO) {
        TypeEntity entity = new TypeEntity();

        entity.setName(TypeDTO.getName());
        entity.setIcon(TypeDTO.getIcon());
        entity.setDescription(TypeDTO.getDescription());
        entity.setId(TypeDTO.getId());

        return entity;
    }

    @Override
    public TypeDTO convertFromEntity(TypeEntity entity) {
        TypeDTO dto = new TypeDTO();

        dto.setName(entity.getName());
        dto.setIcon(entity.getIcon());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());


        return dto;
    }
}
