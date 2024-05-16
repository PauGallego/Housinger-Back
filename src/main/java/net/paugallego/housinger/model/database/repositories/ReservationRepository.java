package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByReservationProperty(PropertyEntity property);
    Optional<List<ReservationEntity>> findByReservationPropertyAndReservationUserAndType(PropertyEntity property, UserEntity user, String type);




}
