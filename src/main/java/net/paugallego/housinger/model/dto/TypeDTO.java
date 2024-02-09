package net.paugallego.housinger.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;
    private String description;
}
