package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import net.paugallego.housinger.model.database.repositories.TypeRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
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
    private CalendarRepository calendarRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Override
    public PropertyEntity convertFromDTO(PropertyDTO propertyDTO) {
        PropertyEntity entity = new PropertyEntity();

        entity.setAddress(propertyDTO.getAddress());
        entity.setDescription(propertyDTO.getDescription());
        entity.setFotos(propertyDTO.getFotos());
        entity.setExtraInfo(propertyDTO.getExtraInfo());
        entity.setNormas(propertyDTO.getNormas());
        entity.setId(propertyDTO.getId());

        if (propertyDTO.getUser() != null && propertyDTO.getUser().getId() != null) {
            UserEntity user = userRepository.findById(propertyDTO.getUser().getId()).orElse(null);
            entity.setUser(user);
        }

        if (propertyDTO.getCalendarId() != null) {
            CalendarEntity calendar = calendarRepository.findById(propertyDTO.getCalendarId()).orElse(null);
            entity.setCalendar(calendar);
        }

        if (propertyDTO.getTypeId() != null) {
            TypeEntity type = typeRepository.findById(propertyDTO.getTypeId()).orElse(null);
            entity.setType(type);
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

        dto.setUser(entity.getUser());

        if (entity.getCalendar() != null) {
            dto.setCalendarId(entity.getCalendar().getId());
        }

        if (entity.getType() != null) {
            dto.setTypeId(entity.getType().getId());
        }

        return dto;
    }
}
