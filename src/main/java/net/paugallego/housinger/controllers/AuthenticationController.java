package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.UserEntity;
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
    public ResponseEntity<?> signup (@RequestBody UserEntity user){

        ResponseEntity<?> response = null;

        user = service.signUpUser(user);

        response =   ResponseEntity.status(HttpStatus.OK).body(user);

        return  response;

    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin (@RequestBody UserEntity user){


        return ResponseEntity.status(HttpStatus.OK).body("Logued");

    }


}
