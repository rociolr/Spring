package com.example.car.service;

import com.example.car.filter.dtos.JwtResponse;
import com.example.car.filter.dtos.LoginRequest;
import com.example.car.filter.dtos.SignUpRequest;
import com.example.car.entity.User;
import com.example.car.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock

    private UserRepository userRepository;
    @Mock
    private UserServiceImpl userServiceImpl;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtServiceImpl jwtServiceImpl;
    @Mock
    private AuthenticationManager authenticationManager;


    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(
                userRepository,
                userServiceImpl,
                passwordEncoder,
                jwtServiceImpl,
                authenticationManager);
    }


    @Test
    void testSignup() throws Exception {
        // No necesitamos @BeforeEach, inicializamos directamente en el test


        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("John");
        signUpRequest.setSurname("Doe");
        signUpRequest.setEmail("john.doe@example.com");
        signUpRequest.setPassword("password");

        User user = User.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .role("ROLE_CLIENT")
                .build();

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userServiceImpl.save(any(User.class))).thenReturn(user);
        when(jwtServiceImpl.generateToken(any(User.class))).thenReturn("jwtToken");

        JwtResponse jwtResponse = authenticationService.signup(signUpRequest);

        assertEquals("jwtToken", jwtResponse.getToken());

    }




    @Test
    void test_Login_Success() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("rocio@mail.com");
        loginRequest.setPassword("password");
        User user = User.builder()
                .name("rocio")
                .surname("lauriño")
                .email("rocio@mail.com")
                .password("encodedPassword")
                .role("ROLE_CLIENT")
                .build();
        Mockito.lenient().when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtServiceImpl.generateToken(any(User.class))).thenReturn("jwtToken");
        JwtResponse jwtResponse = authenticationService.login(loginRequest);
        assertEquals("jwtToken", jwtResponse.getToken());
        Mockito.verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void test_Login_Fail_InvalidEmailOrPassword() {
        // cuando introducimps un email o contraseña invalido
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("rocio@mail.com");
        loginRequest.setPassword("password");
        Mockito.lenient().when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        //comprueba si al llamar a authenticationservice.login lanza ese tipop de una excepcio n
        //asi tomamos la excepcion
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.login(loginRequest);
        });
        assertEquals("invalid email o password", exception.getMessage());
        Mockito.verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


}
















