package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

    List<PropertyEntity> findByCharacteristics(Optional<CharacteristicEntity> characteristic);
    PropertyEntity findByCalendar(CalendarEntity calendar);
    List<PropertyEntity> findByAddressContaining(String location);
    List<PropertyEntity> findByUser(UserEntity userEntity);
}
