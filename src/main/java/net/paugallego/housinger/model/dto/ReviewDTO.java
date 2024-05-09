package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {
    
    private Long id;
    private Long reviewUserId;
    private Long reviewPropertyId;
    private String description;
    private Date date;
    private Float stars;
    private String name;
    private String surname;
    private String picture;

}
