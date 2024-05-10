package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyDTO {

    private Long id;
    private Long userId;
    private String name;
    private String surname;
    private String picture;
    private String address;
    private TypeEntity type;
    private CalendarEntity calendar;
    private String description;
    private List<String> fotos;
    private List<String> normas;
    private String extraInfo;
    private List<CharacteristicDTO> characteristics;
    private Boolean premium;
    private Float stars;
    private Long customerId;
    private List<String> seguridadHogar;

}
