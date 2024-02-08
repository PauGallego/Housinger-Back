package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
