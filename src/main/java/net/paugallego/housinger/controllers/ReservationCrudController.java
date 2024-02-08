package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.entity.ReservationCRUDService;
import net.paugallego.housinger.services.crud.entity.ReviewCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/reservation")
public class ReservationCrudController extends AbstractController<ReservationEntity, ReservationDTO, ReservationCRUDService, ReservationDTOConverter, ReservationRepository, Long> {
}
