package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/user")
public class UserCrudController extends AbstractController<UserEntity, UserDTO, UserCRUDService, UserDTOConverter, UserRepository, Long> {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable Long id ){
        try {

            UserEntity use = userRepository.findById(id).orElse(null);

            if(use.getRoles().contains(RoleEnum.A)){
                return ResponseEntity.status(HttpStatus.OK).body("yes");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No");
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }
}
