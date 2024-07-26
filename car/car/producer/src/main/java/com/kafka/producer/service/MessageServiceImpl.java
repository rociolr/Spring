package com.kafka.producer.service;




import com.example.car.service.CarService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service("MessageServiceImpl")
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl  implements  MessageService{
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired(required = true)
    @Qualifier("CarServiceImpl")
    private CarService carService;

//@KafkaListener(topics = "sample-topic", groupId="1")
    @Override
    public void write(Integer id) {

  // Optional<Car> car= carService.buscarPorId(id);
      String message=  "oferta de coche con id:"+ id.toString() +"teléfono: 657443344";
        System.out.println(message);
        kafkaTemplate.send("sample-topic",message);
    }





    //  @Override
 //   @KafkaListener(topics = "sample-topic", groupId="1")
 /*   public void write(String message) {
        System.out.println(message);
        kafkaTemplate.send("sample-topic",message);
*/
    }

