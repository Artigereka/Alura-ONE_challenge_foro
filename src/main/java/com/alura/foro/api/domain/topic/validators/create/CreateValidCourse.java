package com.alura.foro.api.domain.topic.validators.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.api.domain.course.CourseRepository;
import com.alura.foro.api.domain.topic.CreateTopicDTO;

import jakarta.validation.ValidationException;

@Component
public class CreateValidCourse implements CreateTopicValidator{

    @Autowired
    private CourseRepository repository;

    @Override
    public void validate(CreateTopicDTO data) {

        var courseExists = repository.existsById(data.courseID());
        if (!courseExists) {
            throw new ValidationException("This course doesn't exist");
        }

        var courseEnabled = repository.findById(data.courseID()).get().getActive();
        if (!courseEnabled) {
            throw new ValidationException("This course is not able at the moment");
        }

    }

}
