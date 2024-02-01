package com.alura.foro.domain.topic.validators.service;

import com.alura.foro.domain.topic.CreateTopic;

public interface TopicValidService {
    boolean isDuplicate(CreateTopic topic);
}
