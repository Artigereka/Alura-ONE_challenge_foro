package com.alura.foro.api.domain.topic;

public record UpdateTopicDTO(
        String title,
        String body,
        Long courseID,
        TopicStatus status) {
}
