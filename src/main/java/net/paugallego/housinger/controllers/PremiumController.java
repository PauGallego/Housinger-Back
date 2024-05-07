package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.services.crud.entity.PropertyCharacteristicsCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/premium")
public class PremiumController {


    @Autowired
    UserRepository service;


    @GetMapping("/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable Long id) {
        try {
            Optional<UserEntity> optionalUser = service.findById(id);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();

                if (!user.getRoles().contains(RoleEnum.P)) {
                    user.getRoles().add(RoleEnum.P);
                    service.save(user);
                    return ResponseEntity.status(HttpStatus.OK).body("Usuario añadido a premium correctamente!");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya es premium.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getidPremium(@PathVariable Long id) {
        try {
            Optional<UserEntity> optionalUser = service.findById(id);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();

                if (user.getRoles().contains(RoleEnum.P)) {

                    return ResponseEntity.status(HttpStatus.OK).body("ok");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("no");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }




}
