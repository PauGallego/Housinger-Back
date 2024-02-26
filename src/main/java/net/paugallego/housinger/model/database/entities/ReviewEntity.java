package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reviewUser_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity reviewUser;
    @ManyToOne
    @JoinColumn(name = "reviewProperty_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PropertyEntity reviewProperty;
    private String description;
    private Date date;
    private Float starts;

}