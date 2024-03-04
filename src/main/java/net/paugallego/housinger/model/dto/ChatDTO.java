package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.UserEntity;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatDTO {


    private Long id;
    private List<Date> date;
    private List<String> message;
    private Long userSendId;
    private Long userReceiveId;

}
