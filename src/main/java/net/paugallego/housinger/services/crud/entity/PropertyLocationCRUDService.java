package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.PropertyCharacteristicsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PropertyLocationCRUDService {


    @Autowired
    PropertyRepository propertyRepository;


    @Autowired
    PropertyCharacteristicsDTOConverter dtoConverter;
    public List<PropertyCharacteristicsDTO> findByLocation(String address) {

        List<PropertyEntity> properties = propertyRepository.findByAddressContaining(address);



        return dtoConverter.convertFromEntities(properties);

    }

    public List<PropertyCharacteristicsDTO> findAll() {

        return dtoConverter.convertFromEntities(propertyRepository.findAll());
    }

}
