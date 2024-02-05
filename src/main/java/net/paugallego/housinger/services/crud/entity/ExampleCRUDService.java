package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.ExampleEntity;
import net.paugallego.housinger.model.database.repositories.ExampleRepository;
import net.paugallego.housinger.model.dto.ExampleDTO;
import net.paugallego.housinger.services.crud.dto.ExampleDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ExampleCRUDService extends AbstractCRUDService<ExampleEntity, ExampleDTO, ExampleDTOConverter, ExampleRepository, Long> {
}
