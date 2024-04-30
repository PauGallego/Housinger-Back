package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByReceiver(CustomerEntity customer);
    List<MessageEntity> findBySender(CustomerEntity customer);

}