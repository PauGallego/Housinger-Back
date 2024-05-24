package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCrudControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCrudController userCrudController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    //Test de comprovacion de si un usuario es admin devuelve SI
    public void testGetAdmin_WhenAdminExists_ReturnsYes() {
        Long userId = 1L;
        UserEntity adminUser = new UserEntity();
        adminUser.setId(userId);
        adminUser.getRoles().add(RoleEnum.A);

        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));

        ResponseEntity<?> response = userCrudController.getAdmin(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("yes", response.getBody());
    }

    @Test

    //Test de comprovacion de si un usuario es admin devuelve NO
    public void testGetAdmin_WhenAdminDoesNotExist_ReturnsNo() {

        Long userId = 1L;
        UserEntity nonAdminUser = new UserEntity();
        nonAdminUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(nonAdminUser));

        ResponseEntity<?> response = userCrudController.getAdmin(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No", response.getBody());
    }
}
