package net.paugallego.housinger.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BedDTO {

    @Id
    @GeneratedValue
    private Long id;
    private Long bedTypeId;
    private String number;
}
