package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyReviewsDTOConverter  {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewDTOConverter dtoConverter;


    public PropertyReviewsDTO convertFromEntity(PropertyEntity entity) {

        PropertyReviewsDTO dto = new PropertyReviewsDTO();

        dto.setReviews(dtoConverter.convertFromEntities(reviewRepository.findByReviewProperty(entity)));

        return dto;
    }
}
