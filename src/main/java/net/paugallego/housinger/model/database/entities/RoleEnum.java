package net.paugallego.housinger.model.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleEnum {
    A,E,I,O,U;
}
