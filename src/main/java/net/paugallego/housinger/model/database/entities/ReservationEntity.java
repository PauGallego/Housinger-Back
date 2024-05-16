package net.paugallego.housinger.model.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
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
    @JoinColumn(name = "reservationUser_id", referencedColumnName = "id")
    private UserEntity reservationUser;
    @ManyToOne
    @JoinColumn(name = "reservationProperty_id", referencedColumnName = "id")
    private PropertyEntity reservationProperty;
    private Date dateStart;
    private Date dateEnd;
    private String type;

}