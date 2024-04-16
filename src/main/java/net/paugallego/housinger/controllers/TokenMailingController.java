package net.paugallego.housinger.controllers;

import org.springframework.core.io.Resource;
import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.TokenMailingEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.TokenMailingRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.FindPropertyByLocationDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.entity.PropertyLocationCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(path = "/v1")
public class TokenMailingController {


    @Autowired
    TokenMailingRepository repository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/recover/{token}")
    public ResponseEntity<?> recover(@PathVariable String token) {
        try {


             TokenMailingEntity entity = repository.findByToken(token);


            return ResponseEntity.status(HttpStatus.OK).body("donete");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @GetMapping("/enable/{token}")
    public ResponseEntity<?> enable(@PathVariable String token) {
        try {
            TokenMailingEntity entity = repository.findByToken(token);

            if (Objects.equals(entity.getType(), "enable")){
                entity.getUserEntity().setEnableAccount(true);
                userRepository.save(entity.getUserEntity());
            }else{
                Resource resource = new ClassPathResource("static/error.html");
                return ResponseEntity.ok().body(resource);
            }


            repository.delete(entity);
            Resource resource = new ClassPathResource("static/enable.html");
            return ResponseEntity.ok().body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            Resource resource = new ClassPathResource("static/error.html");
            return ResponseEntity.ok().body(resource);
        }
    }


}
