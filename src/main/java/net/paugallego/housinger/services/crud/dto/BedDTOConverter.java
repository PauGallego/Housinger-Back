package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.dto.BedDTO;
import org.springframework.stereotype.Service;

@Service
public class BedDTOConverter extends AbstractDTOConverter<BedEntity, BedDTO> {
    @Override
    public BedEntity convertFromDTO(BedDTO BedDTO) {
        BedEntity entity = new BedEntity();

        entity.setBedTypeId(BedDTO.getBedTypeId());
        entity.setNumber(BedDTO.getNumber());
        entity.setId(BedDTO.getId());

        return entity;
    }

    @Override
    public BedDTO convertFromEntity(BedEntity entity) {
        BedDTO dto = new BedDTO();

        dto.setBedTypeId(entity.getBedTypeId());
        dto.setNumber(entity.getNumber());
        dto.setId(entity.getId());

        return dto;
    }
}
