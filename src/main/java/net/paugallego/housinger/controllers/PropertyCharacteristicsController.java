package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.dto.FindPropertyByCharacteristicsDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import net.paugallego.housinger.services.crud.entity.PropertyCharacteristicsCRUDService;
import net.paugallego.housinger.services.crud.entity.PropertyReviewsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/v1/propertyCharacteristics")
public class PropertyCharacteristicsController {


    @Autowired
    PropertyCharacteristicsCRUDService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            List<PropertyCharacteristicsDTO> dtos = service.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getByDTO(@RequestBody FindPropertyByCharacteristicsDTO dto) {
        try {
            List<Long> characteristics = dto.getCharacteristics();
            List<PropertyCharacteristicsDTO> dtos = new ArrayList<>();

            List<PropertyCharacteristicsDTO> properties = service.findById(characteristics.get(0));

            for (int i = 1; i < characteristics.size(); i++) {
                Long characteristicId = characteristics.get(i);
                List<PropertyCharacteristicsDTO> characteristicProperties = service.findById(characteristicId);

                properties.retainAll(characteristicProperties);
            }

            dtos.addAll(properties);

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



}
