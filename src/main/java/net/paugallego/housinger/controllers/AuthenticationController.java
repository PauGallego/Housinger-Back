package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.dto.RegisterDTO;
import net.paugallego.housinger.model.dto.LoginDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationController {


    @Autowired
    UserCRUDService service;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signup (@RequestBody RegisterDTO userDTO){

        ResponseEntity<?> response = null;

        UserEntity user = service.signUpUser(userDTO);

        response =   ResponseEntity.status(HttpStatus.OK).body(user);

        return  response;

    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin (@RequestBody LoginDTO user){



        return ResponseEntity.status(HttpStatus.OK).body("Logued");

    }


}
