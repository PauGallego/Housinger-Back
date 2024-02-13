package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.dto.CalendarDTO;
import org.springframework.stereotype.Service;

@Service
public class CalendarDTOConverter extends AbstractDTOConverter<CalendarEntity, CalendarDTO> {
    @Override
    public CalendarEntity convertFromDTO(CalendarDTO CalendarDTO) {
        CalendarEntity entity = new CalendarEntity();

        entity.setFreeDates(CalendarDTO.getFreeDates());
        entity.setReservedDates(CalendarDTO.getReservedDates());
        entity.setId(CalendarDTO.getId());

        return entity;
    }

    @Override
    public CalendarDTO convertFromEntity(CalendarEntity entity) {
        CalendarDTO dto = new CalendarDTO();

        dto.setFreeDates(entity.getFreeDates());
        dto.setReservedDates(entity.getReservedDates());
        dto.setId(entity.getId());

        return dto;
    }
}
