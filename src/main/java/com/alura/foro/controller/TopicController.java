package com.alura.foro.controller;

import com.alura.foro.domain.topic.CreateTopic;
import com.alura.foro.domain.topic.Topic;
import com.alura.foro.domain.topic.validators.service.TopicValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")//Name of the table
public class TopicController {

    private final TopicValidator topicValidator;

    @Autowired
    public TopicController(TopicValidator topicValidator){
        this.topicValidator = topicValidator;
    }

    @PostMapping
    public ResponseEntity createTopic(@RequestBody @Valid CreateTopic createTopic){
        if(topicValidator.isDuplicate(createTopic)){
            return ResponseEntity.badRequest().body("This topic already exists.");
        }
        return ResponseEntity.ok("The topic has been created successfully.");
    }

}
