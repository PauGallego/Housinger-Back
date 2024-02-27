package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BedEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bedType_id", referencedColumnName = "id")
    private BedTypeEntity bedType;
    private String number;
    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private PropertyEntity property;

}

