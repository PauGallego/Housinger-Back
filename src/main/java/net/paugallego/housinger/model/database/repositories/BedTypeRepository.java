package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedTypeRepository extends JpaRepository<BedTypeEntity, Long> {
}
