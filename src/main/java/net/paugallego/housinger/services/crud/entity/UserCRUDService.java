package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserCRUDService extends AbstractCRUDService<UserEntity, UserDTO, UserDTOConverter, UserRepository, Long> implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public UserDetails loadUserById(Long id) {
        return repository.findById(id).get();
    }

    public UserEntity signUpUser(UserEntity user) {
//
//        if (repository.existsUserByEmail(user.getEmail())) {
//            throw new UserManagementException(BodyErrorCode.EMAIL_ALREADY_EXISTS);
//        } else if(repository.existsUserByUsername(user.getUsername())) {
//            throw new UserManagementException(BodyErrorCode.USERNAME_ALREADY_EXISTS);
//        }
//
//        String activationCode = UUID.randomUUID().toString().substring(0, 8);
//        user.setActivationCode(activationCode);
//        user.setEnableAccount(false);
//        user.setRoles(Set.of(RoleEnum.A));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        UserCustomer customer = new UserCustomer();
//
//        UserEntity userObj = DtoConverter.fromDto(user);
//
//        userObj.setCustomer(customer);
//        customer.setUser(userObj);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
