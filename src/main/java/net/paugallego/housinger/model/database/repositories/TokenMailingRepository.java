package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.TokenMailingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMailingRepository extends JpaRepository<TokenMailingEntity, Long> {
    TokenMailingEntity findByToken(String token);
}
