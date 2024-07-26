package com.example.car.controller;

import com.example.car.entity.Car;
import com.example.car.entity.User;
import com.example.car.filter.dtos.CarDto;
import com.example.car.filter.dtos.JwtResponse;
import com.example.car.filter.dtos.SignUpRequest;
import com.example.car.repository.CarRepository;
import com.example.car.repository.UserRepository;
import com.example.car.service.*;
import com.nimbusds.jose.shaded.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(CarController.class)


public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

@MockBean
@Qualifier("JwtServiceImpl")
private JwtService jwtService;
    @MockBean
    @Qualifier("CarServiceImpl")
    private CarService carService;
    @MockBean
    @Autowired(required = true)
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @InjectMocks
   private CarController carController;

    @MockBean
    private CarRepository carRepository;
  @MockBean
    private AuthenticationService authenticationService;


    @BeforeEach
    void setup() {
       carController = new CarController(carService);

            MockitoAnnotations.openMocks(this);
        }


/*
    @Test
    @WithMockUser(username = "test", password = "test", roles = "VENDOR")
    public void testAddCar() throws Exception {
/*
        List<Car> carsToAdd = new ArrayList<>();
        Car car1 = new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        Car car2 = new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        carsToAdd.add(car1);
        carsToAdd.add(car2);
        Gson gson = new Gson();
        String jsonString = gson.toJson(carsToAdd);

        //     JwtResponse jwtResponse = authenticationService.signup(request);
        //    when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtResponse);
        // Calling service method
        // Asserting
        //         List<Car> savedCars = futureSavedCars.get();
        //          assertEquals(2, savedCars.size());

        when(carRepository.saveAll(any())).thenReturn(carsToAdd);

        // Calling service method
        CompletableFuture<List<Car>> futureSavedCars = carService.saveAll(carsToAdd);

        when(carService.saveAll(any())).thenReturn(futureSavedCars);
        mockMvc.perform(MockMvcRequestBuilders.post("/addcar")

                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    */
//    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "CLIENT")
    public void testGetAllJoin() throws Exception {
     List<Car> mockCars = new ArrayList<>();
        mockCars.add(new Car(1, 21, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        mockCars.add(new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        CompletableFuture<List<Car>> futureCars = CompletableFuture.completedFuture(mockCars);
        Mockito.when(carService.findAllJoin()).thenReturn(futureCars);


        mockMvc.perform(get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk());
      /*

        //  List<Car> result = (List<Car>) carController.getAllJoin();
        //  Mockito.when(carService.findAllJoin().join()).thenReturn(mockCars);
        CompletableFuture<List<Car>> futureCars = CompletableFuture.completedFuture(mockCars);
        Mockito.when(carService.findAllJoin()).thenReturn(futureCars);


        mockMvc.perform(get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk());
*/

    }

/*
    @Test
    @WithMockUser(username = "test", password = "test", roles = "CLIENT")
    public void testDeleteAllCars() throws Exception {


      //  JwtResponse jwtResponse = authenticationService.signup(signUpRequest);
        List<Car> mockCars = new ArrayList<>();
        Car car1 = new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        Car car2 = new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        mockCars.add(car1);
        mockCars.add(car2);
        Gson gson = new Gson();
        String jsonString = gson.toJson(mockCars);
        CompletableFuture<List<Car>> futureCars = CompletableFuture.completedFuture(mockCars);
        when(carService.deleteAll(mockCars)).thenReturn(futureCars);
        mockMvc.perform(delete("/deleteallcar")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
*/
    /*
    @WithMockUser(username = "test", password = "test", roles = "VENDOR")
    @Test
    public void testUpdateById() throws Exception {
        Integer carId = 1;
        CarDto carDto = new CarDto(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        Gson gson = new Gson();
        String jsonString = gson.toJson(carDto);


        when(carService.update(any(), any())).thenReturn(new Car());

        mockMvc.perform(put("/updatecar/" + carId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
*/
    @WithMockUser(username = "test", password = "test", roles = "CLIENT")
    @Test
    public void testFindCheapProduct() throws Exception {
        double maxPrice = 1.1;
        List<Car> mockCars = new ArrayList<>();
        Car car1 = new Car(1, 1, "Toyota", 2020, 3, 2, 1.2, "e", "e", "e");
        Car car2 = new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e");
        mockCars.add(car1);
        mockCars.add(car2);
        CompletableFuture<List<Car>> futureCars = CompletableFuture.completedFuture(mockCars);
        Mockito.when(carService.findCheapCar(maxPrice)).thenReturn(futureCars);
        //      when(carService.findCheapCar(maxPrice)).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(get("/findCheapcar/" + maxPrice))
                .andExpect(MockMvcResultMatchers.status().isOk());}

/*
    @Test
    public void testHandleMethodArgumentNotValid() throws Exception {
        CarDto carDto = new CarDto(0, 0, "", 0, 0, 0, 0.0, "", "", ""); // Datos inválidos
        Gson gson = new Gson();
        String jsonString = gson.toJson(carDto);
        mockMvc.perform(post("/addcar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
*/
}