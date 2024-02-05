package net.paugallego.archetype.services.crud.dto;

import net.paugallego.archetype.model.database.entities.ExampleEntity;
import net.paugallego.archetype.model.dto.ExampleDTO;
import org.springframework.stereotype.Service;

@Service
public class ExampleDTOConverter extends AbstractDTOConverter<ExampleEntity, ExampleDTO> {
    @Override
    public ExampleEntity convertFromDTO(ExampleDTO exampleDTO) {
        ExampleEntity entity = new ExampleEntity();
        entity.setExample1(exampleDTO.getExample1());
        entity.setExample2(exampleDTO.getExample2());
        entity.setExample3(exampleDTO.getExample3());
        entity.setExample4(exampleDTO.getExample4());

        return entity;
    }

    @Override
    public ExampleDTO convertFromEntity(ExampleEntity entity) {
        ExampleDTO dto = new ExampleDTO();
        dto.setExample1(entity.getExample1());
        dto.setExample2(entity.getExample2());
        dto.setExample3(entity.getExample3());
        dto.setExample4(entity.getExample4());

        return dto;
    }
}
