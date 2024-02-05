package net.rudahee.archetype.model.database.repositories;

import net.rudahee.archetype.model.database.entities.ExampleEntity;
import net.rudahee.archetype.model.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
