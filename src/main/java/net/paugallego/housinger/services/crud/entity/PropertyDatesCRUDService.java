package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.PropertyCharacteristicsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyDatesCRUDService {


    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    PropertyCharacteristicsDTOConverter dtoConverter;
    public List<PropertyCharacteristicsDTO> findByDate(Date start, Date end) {
        List<PropertyEntity> properties = new ArrayList<>();
        List<CalendarEntity> calendars = calendarRepository.findAll();

        for (CalendarEntity calendar : calendars) {
            boolean isAvailable = true;
            List<Date> reservedDates = calendar.getReservedDates();

            for (Date reservedDate : reservedDates) {
                if (reservedDate.compareTo(start) >= 0 && reservedDate.compareTo(end) <= 0) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                properties.add(propertyRepository.findByCalendar(calendar));
            }
        }

        return dtoConverter.convertFromEntities(properties);

    }

    public List<PropertyCharacteristicsDTO> findAll() {

        return dtoConverter.convertFromEntities(propertyRepository.findAll());
    }

}
