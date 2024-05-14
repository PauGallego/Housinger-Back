package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.FindPropertyByCharacteristicsDTO;
import net.paugallego.housinger.model.dto.FindPropertyByDateDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.entity.PropertyCharacteristicsCRUDService;
import net.paugallego.housinger.services.crud.entity.PropertyDatesCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/propertyCalendar")
public class PropertyCalendarController {

    @Autowired
    PropertyDatesCRUDService service;



    @PostMapping("/get")
    public ResponseEntity<?> getByDTO(@RequestBody FindPropertyByDateDTO dto) {
        try {
            Date start = dto.getStartDate();
            Date end = dto.getEndDate();

            List<PropertyCharacteristicsDTO> dtos = service.findByDate(start,end);


            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            List<PropertyCharacteristicsDTO> dtos = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }



    @GetMapping("/getByProperty/{id}")
    public ResponseEntity<?> getByProperty(@PathVariable Long id) {
        try {
            CalendarDTO dto = service.findByProperty(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }




}
