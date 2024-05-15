package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.PropertyCharacteristicsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PropertyLocationCRUDService {


    @Autowired
    PropertyRepository propertyRepository;


    @Autowired
    PropertyCharacteristicsDTOConverter dtoConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<PropertyCharacteristicsDTO> findByLocation(String address) {

        List<PropertyEntity> properties = propertyRepository.findByAddressContaining(address);



        return dtoConverter.convertFromEntities(properties);

    }

    public List<PropertyCharacteristicsDTO> findByUser(Long userId) {


        UserEntity user = userRepository.findById(userId).orElse(null);


        List<PropertyEntity> properties = propertyRepository.findByUser(user);



        return dtoConverter.convertFromEntities(properties);

    }

    public List<PropertyCharacteristicsDTO> findByCustomer(Long userId) {


        UserEntity user = Objects.requireNonNull(customerRepository.findById(userId).orElse(null)).getUserEntity();


        List<PropertyEntity> properties = propertyRepository.findByUser(user);



        return dtoConverter.convertFromEntities(properties);

    }

    public List<PropertyCharacteristicsDTO> findAll() {

        return dtoConverter.convertFromEntities(propertyRepository.findAll());
    }

}
