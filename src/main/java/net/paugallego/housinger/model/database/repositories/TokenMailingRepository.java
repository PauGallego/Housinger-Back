package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TokenMailingRepository extends JpaRepository<TokenMailingEntity, Long> {
    Optional<TokenMailingEntity> findByToken(String token);
    Optional<TokenMailingEntity> findByUserEntityAndType(UserEntity user, String type);
}
