package net.paugallego.housinger.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BedDTO {

    @Id
    @GeneratedValue
    private Long id;
    private BedTypeEntity bedType;
    private String number;
    private PropertyEntity property;
}
