package net.paugallego.housinger.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    private Long userId;
    private String address;
    private TypeEntity type;
    private CalendarEntity calendar;
    private String description;
    private List<String> fotos;
    private List<String> normas;
    private String extraInfo;
    private List<CharacteristicDTO> characteristics;

}
