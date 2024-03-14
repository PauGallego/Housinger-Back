package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.FindPropertyByCharacteristicsDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyCharacteristicsDTOConverter {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;



    public PropertyCharacteristicsDTO convertFromEntity(PropertyEntity property) {

        PropertyCharacteristicsDTO dto = new PropertyCharacteristicsDTO();
        dto.setPropertyId(property.getId());
        dto.setFoto(property.getFotos().getFirst());
        dto.setOwnerName(property.getUser().getName());
        dto.setAddress(property.getAddress());

        return dto;
    }

    public List<PropertyCharacteristicsDTO> convertFromEntities(List<PropertyEntity> entities) {return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());}

}
