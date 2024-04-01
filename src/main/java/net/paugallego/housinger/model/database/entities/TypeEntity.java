package net.paugallego.housinger.model.database.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;
    private String description;

}

