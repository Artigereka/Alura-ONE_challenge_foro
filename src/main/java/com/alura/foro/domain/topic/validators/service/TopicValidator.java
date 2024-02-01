package com.alura.foro.domain.topic.validators.service;

import com.alura.foro.domain.topic.CreateTopic;
import com.alura.foro.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidator implements TopicValidService{

    @Autowired
    private TopicRepository repository;

    @Override
    public boolean isDuplicate(CreateTopic topic) {
        return repository.existsByTitleAndBody(topic.title(), topic.body());
    }

}
