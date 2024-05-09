package net.paugallego.housinger.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.BedTypeEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BedDTO {

    private Long id;
    private Long bedTypeId;
    private String number;
    private Long propertyId;
    private String type;
    private String icon;
}
