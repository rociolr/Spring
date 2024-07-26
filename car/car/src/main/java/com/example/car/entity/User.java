package com.example.car.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    //se le pone que sea unico el email porque sirve para autenticarlo en la aplicacion
    @Column(unique = true)
    String email;
    String password;
    String role;
    String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //VEREMOS LAS AUTORITYES Q TIENE
       // LISTA DE AUTORITIES DE LOS ROLES Q TENGA ACTIVOS
        return List.of(new SimpleGrantedAuthority(role));
        //por defecto vamos a tener un rol por cada usuario
        //un role debe ser unico e incrementl
    }
//devuelve el nombre del usuario
    @Override
    public String getUsername() {
        return email;
    }
//si la cuenta esta caducada o no
    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }
//si la cuenta no esta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }
//si los credenciales nohan expirado
    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }
    //DEVOLVEMOS TRUE DE TOODS LA CUENTANOESTA LOQUEADA ES DISPONIBLE ETX
//o si esta activo
    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }
}
