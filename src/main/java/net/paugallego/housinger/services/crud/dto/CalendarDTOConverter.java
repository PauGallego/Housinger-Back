package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.CalendarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CalendarDTOConverter extends AbstractDTOConverter<CalendarEntity, CalendarDTO> {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public CalendarEntity convertFromDTO(CalendarDTO calendarDTO) {
        CalendarEntity entity = new CalendarEntity();

        entity.setFreeDates(calendarDTO.getFreeDates());
        entity.setReservedDates(calendarDTO.getReservedDates());
        entity.setId(calendarDTO.getId());

        PropertyEntity property = propertyRepository.findById(calendarDTO.getPropertyId()).orElse(null);
        entity.setProperty(property);

        return entity;
    }

    @Override
    public CalendarDTO convertFromEntity(CalendarEntity entity) {
        CalendarDTO dto = new CalendarDTO();

        dto.setFreeDates(entity.getFreeDates());
        dto.setReservedDates(entity.getReservedDates());
        dto.setId(entity.getId());

        if (entity.getProperty() != null) {
            dto.setPropertyId(entity.getProperty().getId());
        }

        return dto;
    }
}