package net.paugallego.archetype.services.crud.dto;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDTOConverter<E, DTO> {
    /*
        E -> Entity
        DTO -> Data Transfer Object
    */


    public abstract E convertFromDTO(DTO dto);
    public abstract DTO convertFromEntity(E entity);

    public List<E> convertFromDTOs(List<DTO> dtos) {
        return dtos.stream().map(this::convertFromDTO).collect(Collectors.toList());
    }
    public List<DTO> convertFromEntities(List<E> entities) {return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());}

}
