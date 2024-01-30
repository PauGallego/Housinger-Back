package net.rudahee.archetype.controllers;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.repositories.ExampleRepository;
import net.rudahee.archetype.model.dto.ExampleDTO;
import net.rudahee.archetype.services.crud.dto.ExampleDTOConverter;
import net.rudahee.archetype.services.crud.entity.ExampleCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/example")
public class ExampleCrudController extends AbstractController<ExampleEntity, ExampleDTO, ExampleCRUDService, ExampleDTOConverter, ExampleRepository, Long> {
}
