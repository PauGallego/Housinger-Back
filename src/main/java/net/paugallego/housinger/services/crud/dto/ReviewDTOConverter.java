package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewDTOConverter extends AbstractDTOConverter<ReviewEntity, ReviewDTO> {
    @Override
    public ReviewEntity convertFromDTO(ReviewDTO reviewDTO) {
        ReviewEntity entity = new ReviewEntity();

        entity.setDate(reviewDTO.getDate());
        entity.setDescription(reviewDTO.getDescription());
        entity.setReviewUserId(reviewDTO.getReviewUserId());
        entity.setReviewPropertyid(reviewDTO.getReviewPropertyid());
        entity.setStarts(reviewDTO.getStarts());
        entity.setId(reviewDTO.getId());

        return entity;
    }

    @Override
    public ReviewDTO convertFromEntity(ReviewEntity entity) {
        ReviewDTO dto = new ReviewDTO();

        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setReviewUserId(entity.getReviewUserId());
        dto.setReviewPropertyid(entity.getReviewPropertyid());
        dto.setStarts(entity.getStarts());
        dto.setId(entity.getId());

        return dto;
    }
}
