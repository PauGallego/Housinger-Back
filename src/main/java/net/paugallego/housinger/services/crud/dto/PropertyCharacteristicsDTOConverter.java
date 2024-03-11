package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyCharacteristicsDTOConverter {

    @Autowired
    private PropertyRepository propertyRepository;


    public PropertyCharacteristicsDTO convertFromEntity(CharacteristicEntity entity) {

        PropertyCharacteristicsDTO dto = new PropertyCharacteristicsDTO();

        PropertyEntity property = propertyRepository.findByCharacteristics(entity).getFirst();

        dto.setPropertyId(property.getId());
        dto.setFoto(property.getFotos().getFirst());
        dto.setOwnerName(property.getUser().getName());
        dto.setAddress(property.getAddress());

        return dto;
    }
}
