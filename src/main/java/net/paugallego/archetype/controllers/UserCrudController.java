package net.paugallego.archetype.controllers;

import net.paugallego.archetype.model.database.entities.UserEntity;
import net.paugallego.archetype.model.dto.UserDTO;
import net.paugallego.archetype.services.crud.dto.UserDTOConverter;
import net.paugallego.archetype.services.crud.entity.UserCRUDService;
import net.paugallego.archetype.model.database.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/user")
public class UserCrudController extends AbstractController<UserEntity, UserDTO, UserCRUDService, UserDTOConverter, UserRepository, Long> {
}
