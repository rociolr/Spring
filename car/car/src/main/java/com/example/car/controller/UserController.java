package com.example.car.controller;

import com.example.car.filter.dtos.LoginRequest;
import com.example.car.filter.dtos.SignUpRequest;
import com.example.car.service.AuthenticationService;
import com.example.car.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired(required=true)
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signup(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //logear usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {


        return ResponseEntity.ok(authenticationService.login(request));

    }
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    @PostMapping("/userImage/{id}/add")
    //CON LA IMAGEN SE AÑADE A LA BASE DE DATOS
    //USAR TIPO LONGBLOB EN LA BASE DE DATOS PARA LA IMAGEN
    public ResponseEntity<String> addProduct(@PathVariable long id, @RequestParam("imageFile") MultipartFile imageFile){
        try{
            //   para q solo me acepte imagenes de un tipo
    /*  if(!imageFile.getOriginalFilename().contains(".png") II !imageFile.getOriginalFilename().contains("jpg")){
        } return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        */
            userService.addUserImage(id,imageFile);
            return ResponseEntity.ok("IMAGE SUCEFULLY UPLOAD");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    //DEVOLVER LA IMAGEN
    @GetMapping(value="/userImage/{id}")
    //id del usuario del q le hemos guardado la imagen y queremos recuperar la imagen
    //
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id){
        //para q solo me acepte iamgenes de png
        try{
            byte[] imageBytes=userService.getUserImage(id);
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            //enviamos array de bytes con las cabeceras  estado ok
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
