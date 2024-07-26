package com.example.car.filter;

import com.example.car.service.JwtService;
import com.example.car.service.JwtServiceImpl;
import com.example.car.service.UserService;
import com.example.car.service.UserServiceImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired(required = true)
    @Qualifier("JwtServiceImpl")
    private JwtService jwtService;
    @Autowired(required = true)
    @Qualifier("UserServiceImpl")
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterchain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String userEmail;

        if (StringUtils.isEmpty(authHeader)) {
            filterchain.doFilter(request, response);
            return;
        }
        //lo recuperamos a partir del indice 7 q nos trae la cabecera
        //po q las cabeceras empiezan con bearer xxxx
        final String jwt = authHeader.substring(7); // Bearer XXXX
        log.info("JWT -> {}", jwt);
        userEmail = jwtService.extractUserName(jwt);
        //en caso de q el email no este vacion y el contexto eszte a null
        //recuperar los datos del usuario al hacer la peticion
        if (!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //si el token es valido y tiene datos
                //mostrar usuario
                log.info("User - {}", userDetails);
                //añadimos contexto vaciio
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                //crearemos un  nuevo token a partir de los datos del usuario de su email y sus roles q tenga
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterchain.doFilter(request, response);
    }

}






