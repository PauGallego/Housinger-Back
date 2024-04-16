package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMailingRepository extends JpaRepository<TokenMailingEntity, Long> {
    TokenMailingEntity findByToken(String token);
    TokenMailingEntity findByUserEntity(UserEntity user);
}
