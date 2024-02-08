package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.UserEntity;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {


    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @ElementCollection
    private List<String> message;
    private Long userSendId;
    private Long userReceiveId;

}
