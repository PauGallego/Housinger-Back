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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<?> recover(@PathVariable String token) {
        try {
            TokenMailingEntity entity = repository.findByToken(token).orElse(null);

            if (entity == null) {
                Resource resource = new ClassPathResource("static/error.html");
                return ResponseEntity.ok().body(resource);
            }

            if (Objects.equals(entity.getType(), "password")) {
                String htmlContent = "";
                try {

                    Resource resource = new ClassPathResource("static/changepass.html");
                    byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
                    htmlContent = new String(bytes);
                    htmlContent = htmlContent.replace("{{userId}}", String.valueOf(entity.getUserEntity().getId()));
                    htmlContent = htmlContent.replace("{{userName}}", String.valueOf(entity.getUserEntity().getUsername()));
                    htmlContent = htmlContent.replace("{{url}}", url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok().body(htmlContent);
            } else {
                Resource resource = new ClassPathResource("static/error.html");
                return ResponseEntity.ok().body(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Resource resource = new ClassPathResource("static/error.html");
            return ResponseEntity.ok().body(resource);
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
                    "<p>Accede al siguiente enlace para modificarla: " +
                            "<a href=\"" + url + "/v1/recover/" + token2 + "\">Modificar</a></p>");



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
