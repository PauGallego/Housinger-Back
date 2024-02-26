package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.dto.ReservationDTO;
import org.springframework.stereotype.Service;

@Service
public class ReservationDTOConverter extends AbstractDTOConverter<ReservationEntity, ReservationDTO> {
    @Override
    public ReservationEntity convertFromDTO(ReservationDTO ReservationDTO) {
        ReservationEntity entity = new ReservationEntity();

        entity.setDateEnd(ReservationDTO.getDateEnd());
        entity.setDateStart(ReservationDTO.getDateStart());
        entity.setReservationUser(ReservationDTO.getReservationUser());
        entity.setReservationProperty(ReservationDTO.getReservationProperty());
        entity.setId(ReservationDTO.getId());

        return entity;
    }

    @Override
    public ReservationDTO convertFromEntity(ReservationEntity entity) {
        ReservationDTO dto = new ReservationDTO();


        dto.setDateEnd(entity.getDateEnd());
        dto.setDateStart(entity.getDateStart());
        dto.setReservationUser(entity.getReservationUser());
        dto.setReservationProperty(entity.getReservationProperty());
        dto.setId(entity.getId());


        return dto;
    }
}
