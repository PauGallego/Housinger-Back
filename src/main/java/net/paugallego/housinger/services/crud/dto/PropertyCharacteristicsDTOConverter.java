package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


        List<String> fotos = property.getFotos();
        if (fotos != null && !fotos.isEmpty()) {
            dto.setFoto(fotos.iterator().next());
        } else {
            dto.setFoto(null);
        }

        dto.setOwnerName(property.getUser().getCustomerEntity().getName() + " " +  property.getUser().getCustomerEntity().getSurname());
        dto.setAddress(property.getAddress());

        if (property.getUser().getRoles().contains(RoleEnum.P)) {
            dto.setPremium(true);
        } else {
            dto.setPremium(false);
        }
        return dto;
    }

    public List<PropertyCharacteristicsDTO> convertFromEntities(List<PropertyEntity> entities) {return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());}

}
