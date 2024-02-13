package net.paugallego.housinger.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatDTO {


    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<Date> date;
    @ElementCollection
    private List<String> message;
    private Long userSendId;
    private Long userReceiveId;

}
