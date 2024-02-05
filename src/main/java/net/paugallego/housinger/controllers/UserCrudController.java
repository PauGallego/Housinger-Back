package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/user")
public class UserCrudController extends AbstractController<UserEntity, UserDTO, UserCRUDService, UserDTOConverter, UserRepository, Long> {
}
