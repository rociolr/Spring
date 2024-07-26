package com.example.car.service;

import com.example.car.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public User save(User newUser) ;
    public   UserDetailsService userDetailsService() ;

    public void addUserImage(Long id, MultipartFile file) throws IOException;
    public  byte[] getUserImage(Long id);


}
