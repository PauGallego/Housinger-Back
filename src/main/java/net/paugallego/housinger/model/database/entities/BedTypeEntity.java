package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BedTypeEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;



}

