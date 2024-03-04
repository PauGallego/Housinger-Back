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
    private Long userId;
    private String address;
    private Long typeId;
    private Set<Long> characteristicIds;
    private Long calendarId;
    private String description;
    private List<String> fotos;
    private List<String> normas;
    private String extraInfo;
}
