package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.ReviewEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyReviewsDTO {

    private List<ReviewDTO> reviews;
}
