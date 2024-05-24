package net.paugallego.housinger.controllers;

import net.paugallego.housinger.configs.PasswordEncoderConfig;
import net.paugallego.housinger.services.crud.MailService;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;


@RestController
@RequestMapping(path = "/v1")
public class TokenMailingController {


    @Autowired
    TokenMailingRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.default.url}")
    private String url;

    @Autowired
    TokenMailingRepository tokenRepo;

    @Autowired
    MailService mailService;




    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
    @GetMapping("/recover/{token}")
    public ResponseEntity<?> recover(@PathVariable String token) throws IOException {
        try {
            TokenMailingEntity entity = repository.findByToken(token).orElse(null);

            if (entity == null) {
                Resource resource = new ClassPathResource("static/error.html");
                String errorContent = readResourceAsString(resource);
                return ResponseEntity.ok().body(errorContent);
            }

            if (Objects.equals(entity.getType(), "password")) {
                String htmlContent = "";
                try {
                    Resource resource = new ClassPathResource("static/changepass.html");
                    htmlContent = readResourceAsString(resource);
                    htmlContent = htmlContent.replace("{{userId}}", String.valueOf(entity.getUserEntity().getId()));
                    htmlContent = htmlContent.replace("{{userName}}", String.valueOf(entity.getUserEntity().getUsername()));
                    htmlContent = htmlContent.replace("{{url}}", url);
                } catch (IOException e) {
                    e.printStackTrace();
                    Resource errorResource = new ClassPathResource("static/error.html");
                    String errorContent = readResourceAsString(errorResource);
                    return ResponseEntity.ok().body(errorContent);
                }
                return ResponseEntity.ok().body(htmlContent);
            } else {
                Resource resource = new ClassPathResource("static/error.html");
                String errorContent = readResourceAsString(resource);
                return ResponseEntity.ok().body(errorContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Resource resource = new ClassPathResource("static/error.html");
            String errorContent = readResourceAsString(resource);
            return ResponseEntity.ok().body(errorContent);
        }
    }

    private String readResourceAsString(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        }
    }


    @PostMapping("/recover/success")
    public ResponseEntity<Resource> recoverSuccess(@RequestParam Long id, @RequestParam String password) {
        try {

            UserEntity user = userRepository.findById(id).orElse(null);

            if (user != null) {

                String encodedPassword = passwordEncoder.encode(password);

                user.setPassword(encodedPassword);

                userRepository.save(user);

                tokenRepo.findByUserEntityAndType(user, "password").ifPresent(token -> tokenRepo.delete(token));

                Resource resource = new ClassPathResource("static/success.html");
                return ResponseEntity.ok().body(resource);
            } else {

                return ResponseEntity.ok().body(errorResource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Resource errorResource = new ClassPathResource("static/error.html");
            return ResponseEntity.ok().body(errorResource);
        }
    }     Resource errorResource = new ClassPathResource("static/error.html");




    @GetMapping("/enable/{token}")
    public ResponseEntity<?> enable(@PathVariable String token) {
        try {
            TokenMailingEntity entity = repository.findByToken(token).orElse(null);



            if (Objects.equals(entity.getType(), "enable") && !entity.getUserEntity().isEnabled()){
                entity.getUserEntity().setEnableAccount(true);
                userRepository.save(entity.getUserEntity());
                repository.delete(entity);
            }else{
                Resource resource = new ClassPathResource("static/error.html");
                return ResponseEntity.ok().body(resource);
            }



            Resource resource = new ClassPathResource("static/enable.html");
            return ResponseEntity.ok().body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            Resource resource = new ClassPathResource("static/error.html");
            return ResponseEntity.ok().body(resource);
        }
    }

    @GetMapping("/recover/user/{mail}")
    public ResponseEntity<?> recovermail(@PathVariable String mail) {
        try {

            UserEntity user = userRepository.findByMail(mail);

            if (user == null){
                return   ResponseEntity.status(HttpStatus.OK).body("ok");

            }

            TokenMailingEntity token = new TokenMailingEntity();

            String token2 = generateRandomToken();

            TokenMailingEntity token5 = tokenRepo.findByUserEntityAndType(user,"password").orElse(null);


            if (token5 != null){
                tokenRepo.delete(token5);
            }

            token.setUserEntity(user);
            token.setType("password");
            token.setToken(token2);

            tokenRepo.save(token);

            mailService.sendMail(user.getMail(),
                    "Housinger: Modifica tu contraseña!",
                    "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#A5D7E8\" style=\"padding: 20px; border-radius: 10px; margin-top: 20px; margin-bottom: 20px;\">" +
                            "<tr>" +
                            "<td align=\"center\">" +
                            "<p style=\"margin-bottom: 20px;\">Accede al siguiente enlace para modificarla: " +
                            "<a href=\"" + url + "/v1/recover/" + token2 + "\" style=\"color: blue;  font-weight: bold;\">Modificar</a>" +
                            "</p>" +
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







            return   ResponseEntity.status(HttpStatus.OK).body("ok");


        } catch (Exception e) {
            e.printStackTrace();
            Resource resource = new ClassPathResource("static/error.html");
            return ResponseEntity.ok().body(resource);
        }
    }


    private String generateRandomToken() {

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replace("-", "").substring(0, 9)
                + "-" + uuid.toString().replace("-", "").substring(9, 18)
                + "-" + uuid.toString().replace("-", "").substring(18, 27);
        return token;
    }
}
