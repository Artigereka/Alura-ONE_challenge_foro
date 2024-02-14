package com.alura.foro.api.domain.topic.validators.create;

import com.alura.foro.api.domain.topic.CreateTopicDTO;

public interface CreateTopicValidator {
    public void validate(CreateTopicDTO data);
}
