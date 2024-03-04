package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDTOConverter extends AbstractDTOConverter<ReviewEntity, ReviewDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public ReviewEntity convertFromDTO(ReviewDTO reviewDTO) {
        ReviewEntity entity = new ReviewEntity();

        entity.setDate(reviewDTO.getDate());
        entity.setDescription(reviewDTO.getDescription());

        UserEntity reviewUser = userRepository.findById(reviewDTO.getReviewUserId()).orElse(null);
        entity.setReviewUser(reviewUser);

        PropertyEntity reviewProperty = propertyRepository.findById(reviewDTO.getReviewPropertyId()).orElse(null);
        entity.setReviewProperty(reviewProperty);

        entity.setStarts(reviewDTO.getStarts());
        entity.setId(reviewDTO.getId());

        return entity;
    }

    @Override
    public ReviewDTO convertFromEntity(ReviewEntity entity) {
        ReviewDTO dto = new ReviewDTO();

        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());

        if (entity.getReviewUser() != null) {
            dto.setReviewUserId(entity.getReviewUser().getId());
        }

        if (entity.getReviewProperty() != null) {
            dto.setReviewPropertyId(entity.getReviewProperty().getId());
        }

        dto.setStarts(entity.getStarts());
        dto.setId(entity.getId());

        return dto;
    }
}