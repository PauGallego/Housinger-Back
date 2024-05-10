package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.*;
import net.paugallego.housinger.model.dto.PropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PropertyDTOConverter extends AbstractDTOConverter<PropertyEntity, PropertyDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacteristicDTOConverter characteristicDTOConverter;
    
    @Autowired
    private ReviewRepository reviewRepository;



    @Override
    public PropertyEntity convertFromDTO(PropertyDTO propertyDTO) {
        PropertyEntity entity = new PropertyEntity();

        entity.setAddress(propertyDTO.getAddress());
        entity.setDescription(propertyDTO.getDescription());
        entity.setFotos(propertyDTO.getFotos());
        entity.setExtraInfo(propertyDTO.getExtraInfo());
        entity.setNormas(propertyDTO.getNormas());
        entity.setId(propertyDTO.getId());
        entity.setType(propertyDTO.getType());
        entity.setCalendar(propertyDTO.getCalendar());
        entity.setSeguridadHogar(propertyDTO.getSeguridadHogar());

        if (propertyDTO.getUserId() != null) {
            UserEntity user = userRepository.findById(propertyDTO.getUserId()).orElse(null);
            entity.setUser(user);
        }

        if(propertyDTO.getCharacteristics() != null){
            entity.setCharacteristics( characteristicDTOConverter.convertFromDTOs(propertyDTO.getCharacteristics()));
        }



        return entity;
    }

    @Override
    public PropertyDTO convertFromEntity(PropertyEntity entity) {
        PropertyDTO dto = new PropertyDTO();

        dto.setAddress(entity.getAddress());
        dto.setDescription(entity.getDescription());
        dto.setFotos(entity.getFotos());
        dto.setExtraInfo(entity.getExtraInfo());
        dto.setNormas(entity.getNormas());
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setCalendar(entity.getCalendar());
        dto.setSeguridadHogar(entity.getSeguridadHogar());


        if(entity.getCharacteristics() != null){
            dto.setCharacteristics(characteristicDTOConverter.convertFromEntities(entity.getCharacteristics()));

        }

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setName(entity.getUser().getCustomerEntity().getName());
            dto.setSurname(entity.getUser().getCustomerEntity().getSurname());
            dto.setPicture(entity.getUser().getCustomerEntity().getPicture());
            dto.setPremium(entity.getUser().getRoles().contains(RoleEnum.P));
            dto.setCustomerId(entity.getUser().getCustomerEntity().getId());

            List<ReviewEntity> reviewEntities = reviewRepository.findByReviewProperty(entity);

            if (!reviewEntities.isEmpty()) {
                float stars = 0F;
                for (ReviewEntity review : reviewEntities) {
                    stars += review.getStarts();
                }
                dto.setStars(stars / reviewEntities.size());
            }else{
                dto.setStars(0F);
            }
        }


        return dto;
    }
}
