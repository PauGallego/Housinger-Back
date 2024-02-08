package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ReservationCRUDService extends AbstractCRUDService<ReservationEntity, ReservationDTO, ReservationDTOConverter, ReservationRepository, Long> {
}
