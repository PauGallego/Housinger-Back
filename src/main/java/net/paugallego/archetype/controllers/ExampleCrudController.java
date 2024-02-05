package net.paugallego.archetype.controllers;

import net.paugallego.archetype.model.database.entities.ExampleEntity;
import net.paugallego.archetype.model.database.repositories.ExampleRepository;
import net.paugallego.archetype.model.dto.ExampleDTO;
import net.paugallego.archetype.services.crud.dto.ExampleDTOConverter;
import net.paugallego.archetype.services.crud.entity.ExampleCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/example")
public class ExampleCrudController extends AbstractController<ExampleEntity, ExampleDTO, ExampleCRUDService, ExampleDTOConverter, ExampleRepository, Long> {
}
