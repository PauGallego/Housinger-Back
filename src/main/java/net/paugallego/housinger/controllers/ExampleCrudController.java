package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.ExampleEntity;
import net.paugallego.housinger.model.database.repositories.ExampleRepository;
import net.paugallego.housinger.model.dto.ExampleDTO;
import net.paugallego.housinger.services.crud.dto.ExampleDTOConverter;
import net.paugallego.housinger.services.crud.entity.ExampleCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/example")
public class ExampleCrudController extends AbstractController<ExampleEntity, ExampleDTO, ExampleCRUDService, ExampleDTOConverter, ExampleRepository, Long> {
}
