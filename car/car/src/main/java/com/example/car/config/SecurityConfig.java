package com.example.car.config;

import com.example.car.filter.JwtAuthenticationFilter;
import com.example.car.service.UserService;
import com.example.car.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//en el securityconfig generamos un encripator y desencriptador para las contraseñas(passwordencoder)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final JwtAuthenticationFilter jwtAutenticationFilter;

    //filtro para poder filtrar dentro del token
    @Autowired(required = true)
    @Qualifier("UserServiceImpl")
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder; //codificar contraseñas  y a generarlas y luego almacenarlas en bdd -

    @Bean
    //BEAN PARA AUTENTICAR USUARIOS AuthenticatorProvider
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login", "/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/addcar" ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/cars" , "/findCheapcar/{price}" ).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/updatecar/{id}" ).permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/deleteallcar").permitAll()


                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAutenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
