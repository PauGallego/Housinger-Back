package net.paugallego.archetype.model.database.repositories;

import net.paugallego.archetype.model.database.entities.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
}
