package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.CharacteristicEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyDTO {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String address;
    private String type;
    private Long characteristicsId;
    private Long typeId;
    private Long calendarId;
    private String description;
    @ElementCollection
    private List<String> fotos;
    @ElementCollection
    private List<String> normas;
    private String extraInfo;
}
