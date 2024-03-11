package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.dto.PropertyReviewsDTO;
import net.paugallego.housinger.services.crud.entity.PropertyReviewsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/v1/propertyReviews")
public class PropertyReviewsController {


    @Autowired
    PropertyReviewsCRUDService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.PARAMETER_IS_MISSING);
            } else {
                Optional<PropertyReviewsDTO> dto = service.findById(id);
                if (dto.isPresent()) {
                    return ResponseEntity.status(HttpStatus.OK).body(dto.get());
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(ApiErrorEnum.NO_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }
}
