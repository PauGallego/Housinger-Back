package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {

    @Id
    @GeneratedValue
    private Long id;
    private Long reviewUserId;
    private Long reviewPropertyid;
    private String description;
    private Date date;
    private Float starts;
}
