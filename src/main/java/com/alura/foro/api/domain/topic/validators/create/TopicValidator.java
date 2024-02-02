package com.alura.foro.api.domain.topic.validators.create;

import com.alura.foro.api.domain.topic.CreateTopicDTO;
import com.alura.foro.api.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidator implements TopicValidService{

    @Autowired
    private TopicRepository repository;

    @Override
    public void isDuplicated(CreateTopicDTO topic) {
        var duplicated = repository.existsByTitleAndBody(topic.title(), topic.body());
        if (duplicated){
            throw new ValidationException("This topic already exists. Check /topics/" +
                    repository.findByTitle(topic.title()).getId());
        }
    }
}
