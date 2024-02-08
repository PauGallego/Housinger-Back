package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
