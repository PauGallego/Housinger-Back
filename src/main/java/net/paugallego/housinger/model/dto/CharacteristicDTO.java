package net.paugallego.housinger.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacteristicDTO {

    private Long id;
    private String name;
    private String icon;
    private String grupo;
}
