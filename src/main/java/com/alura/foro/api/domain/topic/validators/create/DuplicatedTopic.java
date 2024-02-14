package com.alura.foro.api.domain.topic.validators.create;

import com.alura.foro.api.domain.topic.CreateTopicDTO;
import com.alura.foro.api.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicatedTopic implements CreateTopicValidator {

    @Autowired
    private TopicRepository repository;

    @Override
    public void validate(CreateTopicDTO data) {
        var duplicated = repository.existsByTitleAndBody(data.title(), data.body());
        if (duplicated){
            throw new ValidationException("This topic already exists. Check /topics/" +
                    repository.findByTitle(data.title()).getId());
        }
    }
}
