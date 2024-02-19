package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class BedTypeDTOConverter extends AbstractDTOConverter<BedTypeEntity, BedTypeDTO> {
    @Override
    public BedTypeEntity convertFromDTO(BedTypeDTO BedTypeDTO) {
        BedTypeEntity entity = new BedTypeEntity();

        entity.setIcon(BedTypeDTO.getIcon());
        entity.setName(BedTypeDTO.getName());
        entity.setId(BedTypeDTO.getId());

        return entity;
    }

    @Override
    public BedTypeDTO convertFromEntity(BedTypeEntity entity) {
        BedTypeDTO dto = new BedTypeDTO();

        dto.setIcon(entity.getIcon());
        dto.setName(entity.getName());
        dto.setId(entity.getId());

        return dto;
    }
}
