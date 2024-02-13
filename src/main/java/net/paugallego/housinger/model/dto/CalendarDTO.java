package net.paugallego.housinger.model.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarDTO {


    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<Date> freeDates;
    @ElementCollection
    private List<Date> reservedDates;
}

