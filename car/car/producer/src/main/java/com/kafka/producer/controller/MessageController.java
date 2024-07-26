package com.kafka.producer.controller;

import com.kafka.producer.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {
    @Autowired(required = true)
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;
    @PostMapping("/message/{id}")
    ResponseEntity<?> SendMessage(@PathVariable("id")Integer id){
        messageService.write(id);
        return ResponseEntity.ok().build();

    }

}
