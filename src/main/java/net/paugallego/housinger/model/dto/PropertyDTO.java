package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue
    private Long id;
    private UserEntity user;
    private String address;
    private TypeEntity type;
    private Set<CharacteristicEntity> characteristics;
    private CalendarEntity calendar;
    private String description;
    @ElementCollection
    private List<String> fotos;
    @ElementCollection
    private List<String> normas;
    private String extraInfo;
}
