package net.paugallego.housinger.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacteristicDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;
    private String description;
}
