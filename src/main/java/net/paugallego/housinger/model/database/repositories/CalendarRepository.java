package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    CalendarEntity findByProperty(PropertyEntity property);

}
