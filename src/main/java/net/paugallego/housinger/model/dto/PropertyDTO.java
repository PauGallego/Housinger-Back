package net.paugallego.housinger.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long id;
    private UserEntity user;
    private String address;
    private TypeEntity type;
    private Set<CharacteristicEntity> characteristics;
    private CalendarEntity calendar;
    private String description;
    private List<String> fotos;
    private List<String> normas;
    private String extraInfo;
}
