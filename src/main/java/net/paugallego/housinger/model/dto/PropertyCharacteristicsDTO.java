package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyCharacteristicsDTO {

    private Long propertyId;
    private String foto;
    private String address;
    private String ownerName;
}
