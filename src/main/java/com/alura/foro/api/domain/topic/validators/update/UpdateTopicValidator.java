package com.alura.foro.api.domain.topic.validators.update;

import com.alura.foro.api.domain.topic.UpdateTopicDTO;

public interface UpdateTopicValidator {
    public void validate(UpdateTopicDTO data);
}
