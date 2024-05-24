package net.paugallego.housinger.repositories;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.repositories.CharacteristicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CharacteristicRepositorySystemTest {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Test
    //Este test es para comprobar que se puede guardar y volcar (flush) una caracteristica
    public void testSaveAndFlush_Success() {

        CharacteristicEntity characteristic = new CharacteristicEntity();
        characteristic.setName("Test Characteristic");
        characteristic.setIcon("Test Icon");
        characteristic.setGrupo("Test Grupo");

        CharacteristicEntity savedCharacteristic = characteristicRepository.saveAndFlush(characteristic);

        assertEquals(characteristic.getName(), savedCharacteristic.getName());
        assertEquals(characteristic.getIcon(), savedCharacteristic.getIcon());
        assertEquals(characteristic.getGrupo(), savedCharacteristic.getGrupo());
        assertTrue(savedCharacteristic.getId() > 0);
    }

    @Test
    //Este test es para comprobar que se puede borrar una caracteristica correctamente
    public void testDelete_Success() {

        CharacteristicEntity characteristic = new CharacteristicEntity();
        characteristic.setName("Test Characteristic");

        CharacteristicEntity savedCharacteristic = characteristicRepository.save(characteristic);

        characteristicRepository.delete(savedCharacteristic);

        Optional<CharacteristicEntity> deletedCharacteristic = characteristicRepository.findById(savedCharacteristic.getId());
        assertFalse(deletedCharacteristic.isPresent(), "La característica no debería estar presente después de ser borrada.");
    }
}
