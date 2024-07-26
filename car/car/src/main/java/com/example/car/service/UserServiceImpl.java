package com.example.car.service;

import com.example.car.entity.User;
import com.example.car.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service("UserServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }


    @Override
    public void addUserImage(Long id, MultipartFile file) throws IOException {
        User users = userRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("saving image");
        //seteo imagen con base 64
        users.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        //guardamos la entidad
        userRepository.save(users);


    }

    @Override
    public byte[] getUserImage(Long id) {
        //buscammos la entidad enla base de datos y en el caso de q exista lo decodifiamos para poder devolverla en nuestra respuesta http
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return Base64.getDecoder().decode(user.getImage()); //lo pasa a byte

    }

}
