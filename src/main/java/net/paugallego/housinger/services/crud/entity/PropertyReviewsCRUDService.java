package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import net.paugallego.housinger.services.crud.dto.PropertyReviewsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyReviewsCRUDService {


    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    PropertyReviewsDTOConverter dtoConverter;
    public Optional<PropertyReviewsDTO> findById(Long id){
        Optional<PropertyEntity> entity = propertyRepository.findById(id);

        return  entity.map(e -> dtoConverter.convertFromEntity(e));

    }
}
