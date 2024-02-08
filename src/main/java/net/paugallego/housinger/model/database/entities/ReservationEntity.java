package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reservationUser_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity reservationUser;
    @Column(name = "reservationUser_id")
    private Long reservationUserId;
    @ManyToOne
    @JoinColumn(name = "reservationProperty_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PropertyEntity reservationProperty;
    @Column(name = "reservationProperty_id")
    private Long reservationPropertyId;
    private Date dateStart;
    private Date dateEnd;

}