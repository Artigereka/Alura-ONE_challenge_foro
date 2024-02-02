package com.alura.foro.api.domain.topic.validators.create;

import com.alura.foro.api.domain.topic.CreateTopicDTO;

public interface TopicValidService {
    public void isDuplicated(CreateTopicDTO topic);
}
