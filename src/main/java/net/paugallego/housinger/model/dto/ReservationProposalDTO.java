package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationProposalDTO {

    private ReservationDTO proposerDTO;
    private ReservationDTO receiverDTO;

}

