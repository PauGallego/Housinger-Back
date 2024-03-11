package net.paugallego.housinger.model.database.repositories;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByReviewProperty(PropertyEntity reviewProperty);
}

