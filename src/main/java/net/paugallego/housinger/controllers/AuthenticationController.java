package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.TokenMailingEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.TokenMailingRepository;
import net.paugallego.housinger.model.dto.RegisterDTO;
import net.paugallego.housinger.model.dto.LoginDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.MailService;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationController {


    @Autowired
    UserCRUDService service;

    @Autowired
    MailService mailService;

    @Autowired
    TokenMailingRepository tokenRepo;

    @Value("${spring.default.url}")
    private String url;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signup (@RequestBody RegisterDTO userDTO){

        ResponseEntity<?> response = null;

        UserEntity user = service.signUpUser(userDTO);

        response =   ResponseEntity.status(HttpStatus.OK).body(user);

        String token = generateRandomToken();

        TokenMailingEntity tokenMailingEntity = new TokenMailingEntity();

        tokenMailingEntity.setUserEntity(user);
        tokenMailingEntity.setType("enable");
        tokenMailingEntity.setToken(token);

        tokenRepo.save(tokenMailingEntity);

        mailService.sendMail(userDTO.getMail(),
                "Housinger: Activa tu cuenta!",
                "<p>Cuenta creada con éxito, accede al siguiente enlace para activarla: " +
                        "<a href=\"" + url + "/v1/enable/" + token + "\">Activar cuenta</a></p>");



        return  response;

    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin (@RequestBody LoginDTO user){



        return ResponseEntity.status(HttpStatus.OK).body("Logued");

    }

    private String generateRandomToken() {

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replace("-", "").substring(0, 9)
                + "-" + uuid.toString().replace("-", "").substring(9, 18)
                + "-" + uuid.toString().replace("-", "").substring(18, 27);
        return token;
    }

}
