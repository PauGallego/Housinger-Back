package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.BedTypeRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedDTOConverter extends AbstractDTOConverter<BedEntity, BedDTO> {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BedTypeRepository bedTypeRepository;

    @Override
    public BedEntity convertFromDTO(BedDTO bedDTO) {
        BedEntity entity = new BedEntity();

        entity.setNumber(bedDTO.getNumber());
        entity.setId(bedDTO.getId());

        PropertyEntity property = propertyRepository.findById(bedDTO.getPropertyId()).orElse(null);
        entity.setProperty(property);

        BedTypeEntity bedType = bedTypeRepository.findById(bedDTO.getBedTypeId()).orElse(null);
        entity.setBedType(bedType);

        return entity;
    }

    @Override
    public BedDTO convertFromEntity(BedEntity entity) {
        BedDTO dto = new BedDTO();

        dto.setNumber(entity.getNumber());
        dto.setId(entity.getId());

        if (entity.getProperty() != null) {
            dto.setPropertyId(entity.getProperty().getId());
        }

        if (entity.getBedType() != null) {
            dto.setBedTypeId(entity.getBedType().getId());
        }

        return dto;
    }
}
