package net.paugallego.housinger.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.dto.BedTypeDTO;
import net.paugallego.housinger.services.crud.dto.BedTypeDTOConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedTypeDTOConverterIntegrationTest {

    private BedTypeDTOConverter bedTypeDTOConverter;

    @BeforeEach
    public void setUp() {
        bedTypeDTOConverter = new BedTypeDTOConverter();
    }

    @Test
    //Este test es para comprovar que se puede pasar de DTO a entidad correctamente
    public void testConvertFromDTO_Success() {
        // Arrange
        BedTypeDTO bedTypeDTO = new BedTypeDTO();
        bedTypeDTO.setId(1L);
        bedTypeDTO.setIcon("icon_url");
        bedTypeDTO.setName("King Size");

        // Act
        BedTypeEntity result = bedTypeDTOConverter.convertFromDTO(bedTypeDTO);

        // Assert
        assertEquals(bedTypeDTO.getId(), result.getId());
        assertEquals(bedTypeDTO.getIcon(), result.getIcon());
        assertEquals(bedTypeDTO.getName(), result.getName());
    }

    @Test
    //Este test es para comprovar que se puede pasar de entidad a DTO correctamente
    public void testConvertFromEntity_Success() {
        // Arrange
        BedTypeEntity bedTypeEntity = new BedTypeEntity();
        bedTypeEntity.setId(1L);
        bedTypeEntity.setIcon("icon_url");
        bedTypeEntity.setName("King Size");

        // Act
        BedTypeDTO result = bedTypeDTOConverter.convertFromEntity(bedTypeEntity);

        // Assert
        assertEquals(bedTypeEntity.getId(), result.getId());
        assertEquals(bedTypeEntity.getIcon(), result.getIcon());
        assertEquals(bedTypeEntity.getName(), result.getName());
    }
}
