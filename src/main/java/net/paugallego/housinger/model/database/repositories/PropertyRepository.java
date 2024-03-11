package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

    List<PropertyEntity> findByCharacteristics(CharacteristicEntity characteristic);
}
