package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.services.crud.dto.AbstractDTOConverter;
import net.paugallego.housinger.services.crud.entity.AbstractCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController<E, DTO, CS extends AbstractCRUDService<E, DTO, CC, R , ID>, CC extends AbstractDTOConverter<E, DTO>, R extends JpaRepository<E, ID>, ID> {
/*
    E -> Entity
    DTO -> DTO of entity
    CS -> Crud Service
    CC -> DTO <-> Entity Converver
    R -> Repository
    ID -> Id type
 */
    @Autowired
    CS service;

    @Autowired
    CC converter;
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable ID id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.PARAMETER_IS_MISSING);
            } else {
                Optional<DTO> dto = service.findById(id);
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

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            List<DTO> dtos = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.BODY_IS_MISSING);
            } else {
                DTO savedDto = service.save(dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
            }
        } catch (Exception e) {
            System.out.println("Received DTO for update operation: " + dto.toString());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @PostMapping("/save/all")
    public ResponseEntity<?> saveAll(@RequestBody List<DTO> dtos) {
        try {
            if (dtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.BODY_IS_MISSING);
            } else {
                List<DTO> savedDtos = service.saveAll(dtos);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDtos);
            }
        } catch (Exception e) {
            System.out.println("Received DTOS for update operation: " + dtos.toString());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @PutMapping("/save")
    public ResponseEntity<?> update(@RequestBody DTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.BODY_IS_MISSING);
            } else {


                DTO savedDto = service.save(dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
            }
        } catch (Exception e) {

            System.out.println("Received DTO for update operation: " + dto.toString());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @PutMapping("/save/all")
    public ResponseEntity<?> updateAll(@RequestBody List<DTO> dtos) {
        try {
            if (dtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.BODY_IS_MISSING);
            } else {
                List<DTO> savedDtos = service.saveAll(dtos);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDtos);
            }
        } catch (Exception e) {

            System.out.println("Received DTOS for update operation: " + dtos.toString());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody DTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.BODY_IS_MISSING);
            } else {
                service.delete(dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(ApiErrorEnum.NO_ERROR);
            }
        } catch (Exception e) {
            System.out.println("Received DTO for update operation: " + dto.toString());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorEnum.PARAMETER_IS_MISSING);
            } else {
                service.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(ApiErrorEnum.NO_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }
}
