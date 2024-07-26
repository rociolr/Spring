package com.example.car.service;

import com.example.car.entity.User;
import com.example.car.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Test
    void test_save() {
        User user = new User(2L, "hola", "hola", "hola", "hola", "hola","e");

        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.save(user);
        assertEquals(result, user);
    }
}
