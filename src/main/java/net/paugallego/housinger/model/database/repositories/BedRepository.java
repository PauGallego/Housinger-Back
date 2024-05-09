package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<BedEntity, Long> {

    List<BedEntity> findByProperty(PropertyEntity property);
}
