package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.CustomerDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.CustomerDTOConverter;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.CustomerCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/customer")
public class CustomerCrudController extends AbstractController<CustomerEntity, CustomerDTO, CustomerCRUDService, CustomerDTOConverter, CustomerRepository, Long> {
}
