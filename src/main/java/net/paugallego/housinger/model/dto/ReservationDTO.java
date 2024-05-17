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
    private String proposerName;
    private String proposerSurname;
    private String proposerPicture;
    private String propertyPicture;
    private String propertyAddress;
    private Long receiverUserId;
    private String type;
    private Long reservationCustomerId;
    private String receiverUserName;
    private String receiverUserSurname;
    private String receiverPicture;



}
