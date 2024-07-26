package com.example.car.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.car.filter.dtos.JwtResponse;
import com.example.car.filter.dtos.LoginRequest;
import com.example.car.filter.dtos.SignUpRequest;
import com.example.car.service.AuthenticationService;
import com.example.car.service.JwtServiceImpl;
import com.example.car.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    @Qualifier("JwtServiceImpl")
    private JwtServiceImpl jwtService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;
    @MockBean
    private AuthenticationService authenticationService;


    @Autowired
    private UserController userController;


    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testSignup_Success() throws Exception {
        SignUpRequest request = new SignUpRequest();
        request.setName("John");
        request.setSurname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        Gson gson = new Gson();
        String jsonString = gson.toJson(request);

        JwtResponse jwtResponse = authenticationService.signup(request);


        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtResponse);
//.------
        mockMvc
                .perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(status().isOk());
    }


    @Test
    public void testSignup_Failure() throws Exception {
        //      SignUpRequest request = new SignUpRequest();
        // Set the required fields for the request object
        //     when(authenticationService.signup(request)).thenThrow(new RuntimeException("Signup failed"));
/*
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/signup"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()) ;
*/


        SignUpRequest request = new SignUpRequest();
        request.setName("");
        request.setSurname("");
        request.setEmail("");
        request.setPassword("");

        Gson gson1 = new Gson();
        String jsonString1 = gson1.toJson(request);

        //     JwtResponse jwtResponse = authenticationService.signup(request);


        //   when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtResponse);
        when(authenticationService.signup(request)).thenThrow(new RuntimeException("Signup failed"));
//.------
        mockMvc
                .perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString1))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void testLogin_Success() throws Exception {
        LoginRequest request = new LoginRequest("@EMAIL", "USER");

        Gson gson1 = new Gson();
        String jsonString1 = gson1.toJson(request);
        JwtResponse jwtResponse = authenticationService.login(request);


        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtResponse);
//.------
        mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString1))
                .andExpect(status().isOk());
    }


}




