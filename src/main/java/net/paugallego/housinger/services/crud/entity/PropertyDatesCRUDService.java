package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.CalendarDTOConverter;
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

    @Autowired
    CalendarDTOConverter calendarDTOConverter;


    public List<PropertyCharacteristicsDTO> findByDate(Date start, Date end) {
        List<PropertyEntity> properties = new ArrayList<>();
        List<CalendarEntity> calendars = calendarRepository.findAll();

        System.out.println("start: " + start);
        System.out.println("end: " + end);

        for (CalendarEntity calendar : calendars) {
            boolean isAvailable = true;
            List<Date> reservedDates = calendar.getReservedDates();

            System.out.println("Reserved dates for calendar " + calendar.getId() + ": " + reservedDates);

            for (int i = 0; i < reservedDates.size(); i += 2) {
                Date reservedStart = reservedDates.get(i);
                Date reservedEnd = reservedDates.get(i + 1);

                System.out.println("Checking reserved range: " + reservedStart + " - " + reservedEnd);

                if (!(end.before(reservedStart) || start.after(reservedEnd))) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                PropertyEntity property = propertyRepository.findByCalendar(calendar);
                if (property != null) {
                    properties.add(property);
                }
            }
        }

        return dtoConverter.convertFromEntities(properties);
    }



    public List<PropertyCharacteristicsDTO> findAll() {

        return dtoConverter.convertFromEntities(propertyRepository.findAll());
    }


    public CalendarDTO findByProperty(Long id){

        PropertyEntity property = propertyRepository.findById(id).orElse(null);


        assert property != null;
        return  calendarDTOConverter.convertFromEntity(property.getCalendar());
    }

}
