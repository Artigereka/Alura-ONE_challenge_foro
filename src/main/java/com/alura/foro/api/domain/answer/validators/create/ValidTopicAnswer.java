package com.alura.foro.api.domain.answer.validators.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.api.domain.answer.CreateAnswerDTO;
import com.alura.foro.api.domain.topic.TopicStatus;
import com.alura.foro.api.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidTopicAnswer implements CreateAnswerValidator{

    @Autowired
    private TopicRepository repository;

    @Override
    public void validate(CreateAnswerDTO data) {

        var topicExists = repository.existsById(data.topicId());

        if (!topicExists) {
            throw new ValidationException("This topic doesn't exist");
        }

        var topicOpen = repository.findById(data.topicId()).get().getStatus();

        if (topicOpen != TopicStatus.OPEN) {
            throw new ValidationException("This topic isn't open");
        }

    }

}
