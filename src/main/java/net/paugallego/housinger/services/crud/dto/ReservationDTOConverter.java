package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationDTOConverter extends AbstractDTOConverter<ReservationEntity, ReservationDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public ReservationEntity convertFromDTO(ReservationDTO reservationDTO) {
        ReservationEntity entity = new ReservationEntity();

        entity.setDateEnd(reservationDTO.getDateEnd());
        entity.setDateStart(reservationDTO.getDateStart());

        UserEntity reservationUser = userRepository.findById(reservationDTO.getReservationUserId()).orElse(null);
        entity.setReservationUser(reservationUser);

        PropertyEntity reservationProperty = propertyRepository.findById(reservationDTO.getReservationPropertyId()).orElse(null);
        entity.setReservationProperty(reservationProperty);

        entity.setId(reservationDTO.getId());

        return entity;
    }

    @Override
    public ReservationDTO convertFromEntity(ReservationEntity entity) {
        ReservationDTO dto = new ReservationDTO();

        dto.setDateEnd(entity.getDateEnd());
        dto.setDateStart(entity.getDateStart());

        if (entity.getReservationUser() != null) {
            dto.setReservationUserId(entity.getReservationUser().getId());
        }

        if (entity.getReservationProperty() != null) {
            dto.setReservationPropertyId(entity.getReservationProperty().getId());
        }

        dto.setId(entity.getId());

        return dto;
    }
}
