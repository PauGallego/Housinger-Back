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
    @JoinColumn(name = "bedType_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BedTypeEntity bedType;
    @Column(name = "bedType_id")
    private Long bedTypeId;
    private String number;

}

