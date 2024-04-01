package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    
    private Long id;
    private Long reservationUserId;
    private Long reservationPropertyId;
    private Date dateStart;
    private Date dateEnd;
}
