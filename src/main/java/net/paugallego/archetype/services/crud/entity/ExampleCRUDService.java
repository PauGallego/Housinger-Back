package net.paugallego.archetype.services.crud.entity;

import net.paugallego.archetype.model.database.entities.ExampleEntity;
import net.paugallego.archetype.model.database.repositories.ExampleRepository;
import net.paugallego.archetype.model.dto.ExampleDTO;
import net.paugallego.archetype.services.crud.dto.ExampleDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ExampleCRUDService extends AbstractCRUDService<ExampleEntity, ExampleDTO, ExampleDTOConverter, ExampleRepository, Long> {
}
