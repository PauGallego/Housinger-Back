package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<Date> freeDates;
    @ElementCollection
    private List<Date> reservedDates;
    @OneToOne(mappedBy = "calendar")
    @JoinColumn(name = "property_id" , referencedColumnName = "id")
    private PropertyEntity property;
}

