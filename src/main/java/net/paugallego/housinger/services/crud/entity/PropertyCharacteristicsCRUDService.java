package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.dto.CharacteristicDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import net.paugallego.housinger.services.crud.dto.PropertyCharacteristicsDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.dto.PropertyReviewsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyCharacteristicsCRUDService {


    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    CharacteristicRepository characteristicRepository;

    @Autowired
    PropertyCharacteristicsDTOConverter dtoConverter;
    public List<PropertyCharacteristicsDTO> findById(Long id){

        Optional<CharacteristicEntity> characteristic = characteristicRepository.findById(id);

        List<PropertyEntity> propiedades =  propertyRepository.findByCharacteristics(characteristic);

        return dtoConverter.convertFromEntities(propiedades);


    }

}
