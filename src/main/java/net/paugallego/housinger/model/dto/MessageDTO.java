package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.Status;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {


    private Long id;
    private Long senderId;
    private Long receiverId;
    private String message;
    private String date;
    private Status status;
}
