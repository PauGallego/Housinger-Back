package net.rudahee.archetype.services.crud.entity;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.repositories.ExampleRepository;
import net.rudahee.archetype.model.dto.ExampleDTO;
import net.rudahee.archetype.services.crud.dto.ExampleDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ExampleCRUDService extends AbstractCRUDService<ExampleEntity, ExampleDTO, ExampleDTOConverter, ExampleRepository, Long> {
}
