package net.rudahee.archetype.controllers;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.entities.UserEntity;
import net.rudahee.archetype.model.database.repositories.ExampleRepository;
import net.rudahee.archetype.model.database.repositories.UserRepository;
import net.rudahee.archetype.model.dto.ExampleDTO;
import net.rudahee.archetype.model.dto.UserDTO;
import net.rudahee.archetype.services.crud.dto.ExampleDTOConverter;
import net.rudahee.archetype.services.crud.dto.UserDTOConverter;
import net.rudahee.archetype.services.crud.entity.ExampleCRUDService;
import net.rudahee.archetype.services.crud.entity.UserCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/user")
public class UserCrudController extends AbstractController<UserEntity, UserDTO, UserCRUDService, UserDTOConverter, UserRepository, Long> {
}
