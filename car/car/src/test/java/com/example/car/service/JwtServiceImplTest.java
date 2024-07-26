package com.example.car.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtServiceImpl jwtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtServiceImpl();
        jwtService.jwtSecretKey = "MTc1MDQxOWEwZjUxNTAxNjBkYWRhM2QxZmU4NmIzY2QwM2M3N2FiNjgyMTI5NjE0N2I0NDdiZmY2ZjQ0NWNkZDU1ZmY5NzNjMTRlYTc4NGJkZDE5YTMxMDcxMTU4NWFiZjc2Mzk2NmZmYTc4NGYyOGM2YTc2MGQxMjk5NjY1ZmE="; // Asignar clave secreta para pruebas
        jwtService.jwtExpirationMs = 3600000L; // Expiración de 1 hora para pruebas
    }

    @Test
    public void testGenerateToken() {
        // Configurar el mock de UserDetails
        when(userDetails.getUsername()).thenReturn("testuser");

        // Llamar al método generateToken con el mock de UserDetails
        String token = jwtService.generateToken(userDetails);

        // Verificar que el token no es nulo
        assertNotNull(token);
        // Aquí podrías realizar más verificaciones del token generado si es necesario
    }
}

