package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ReviewCRUDService extends AbstractCRUDService<ReviewEntity, ReviewDTO, ReviewDTOConverter, ReviewRepository, Long> {
}
