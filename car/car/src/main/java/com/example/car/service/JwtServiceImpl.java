package com.example.car.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Service("JwtServiceImpl")
public class JwtServiceImpl implements JwtService {
    @Value("${token.secret.key}")
    public String jwtSecretKey;
    ///la firma de nuestrra api
    //para poder firmar los token y comppprobar q el token lo hems validado nosotros
    //2 propiedades secretkey y mes dre expiracion
    @Value("${token.expirationms}")
    public Long jwtExpirationMs;

    //milisegundos: token de velocidad para el token q generemos milisegundos
    //tiempo de caducidad del token q generemos
    //metodo para estructurar el user name dese un token
    public String extractUserName(String token) {
        //le pasamos el token para extraer el username y el campo del token q queremoss recuperar
        return extractClaim(token, Claims::getSubject);
    }

    //generar token
    public String generateToken(UserDetails userDetails) {
        return generatedToken(new HashMap<>(), userDetails);


    }

    //ver si el token es valido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        //le pasamos el tocken
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        //recuperamos todos los claims q tengan el token
        final Claims claims = extractAllClaims(token);
        //  los almacenaremos en claimresolver
        return claimsResolver.apply(claims);
    }

    private String generatedToken(Map<String, Object> extraClaim, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                //fecha en al q creamos el token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                //fecha de expiracion
                //añadimos la clave firmad con el algoritm de firmado
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    //fecha de expiracion del token
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
