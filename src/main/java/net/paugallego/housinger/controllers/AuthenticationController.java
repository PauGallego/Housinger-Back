package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.TokenMailingEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.TokenMailingRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.RegisterDTO;
import net.paugallego.housinger.model.dto.LoginDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.MailService;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationController {


    @Autowired
    UserCRUDService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MailService mailService;

    @Autowired
    TokenMailingRepository tokenRepo;


    @Value("${spring.default.url}")
    private String url;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody RegisterDTO userDTO) {

        ResponseEntity<?> response = null;

        TokenMailingEntity tokenMailingEntity = null;
        try {


            List<CustomerEntity>  lista = customerRepository.findAll();
            for(CustomerEntity customer:lista){
                if (customer.getUserEntity() == null){
                    customerRepository.delete(customer);
                }
            }

            String token = generateRandomToken();

            tokenMailingEntity = new TokenMailingEntity();

            UserEntity user = service.signUpUser(userDTO);
            tokenMailingEntity.setUserEntity(user);
            tokenMailingEntity.setType("enable");
            tokenMailingEntity.setToken(token);
            tokenRepo.save(tokenMailingEntity);

            mailService.sendMail(userDTO.getMail(),
                    "Housinger: Activa tu cuenta!",
                    "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#A5D7E8\" style=\"padding: 20px; border-radius: 10px; margin-top: 20px; margin-bottom: 20px;\">" +
                            "<tr>" +
                            "<td align=\"center\">" +
                            "<p style=\"text-align: center; margin-bottom: 20px;\">Cuenta creada con éxito, accede al siguiente enlace para activarla: " +
                            "<a href=\"" + url + "/v1/enable/" + token + "\" style=\"color: black; text-decoration: none;\">Activar cuenta</a></p>" +
                            "</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td align=\"center\">" +
                            "<table cellspacing=\"0\" cellpadding=\"0\">" +
                            "<tr>" +
                            "<td style=\"padding-right: 10px;\">" +
                            "<img src=\"https://housingerapi.paugallego.com/v1/fileCustomer/download/logo.png\" alt=\"logo\" width=\"150\" height=\"auto\" style=\"margin-right: 10px;\" />" +
                            "</td>" +
                            "<td style=\"font-size: 24px; color: #7189E9;\">" +
                            "<strong>HOUSINGER</strong>" +
                            "</td>" +
                            "</tr>" +
                            "</table>" +
                            "</td>" +
                            "</tr>" +
                            "</table>");






            response = ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (DataIntegrityViolationException e) {
            String sqlErrorMessage = e.getRootCause().getMessage();

            response = ResponseEntity.status(HttpStatus.CONFLICT).body(sqlErrorMessage);

            if (tokenMailingEntity != null) {

                UserEntity user = tokenMailingEntity.getUserEntity();

                if (user != null) {
                    customerRepository.delete(user.getCustomerEntity());
                    userRepository.delete(user);
                }

                tokenRepo.delete(tokenMailingEntity);
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }

        return response;
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
