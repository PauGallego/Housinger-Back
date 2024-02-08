package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.ReviewCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/review")
public class ReviewCrudController extends AbstractController<ReviewEntity, ReviewDTO, ReviewCRUDService, ReviewDTOConverter, ReviewRepository, Long> {
}
