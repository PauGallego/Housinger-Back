package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {

    @Id
    @GeneratedValue
    private Long id;
    private UserEntity reservationUser;
    private PropertyEntity reservationProperty;
    private Date dateStart;
    private Date dateEnd;
}
