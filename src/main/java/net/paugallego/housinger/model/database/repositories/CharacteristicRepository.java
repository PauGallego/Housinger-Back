package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<CharacteristicEntity, Long> {


}
