package com.example.car.service;


import com.example.car.filter.dtos.JwtResponse;
import com.example.car.filter.dtos.LoginRequest;
import com.example.car.filter.dtos.SignUpRequest;
import com.example.car.entity.User;
import com.example.car.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired(required = true)
    @Qualifier("JwtServiceImpl")

    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse signup(SignUpRequest request) throws Exception {
        User user = User
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_CLIENT")
                .image("r")
                .build();
        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();


    }

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("invalid email o password"));
        var jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();

    }
}
