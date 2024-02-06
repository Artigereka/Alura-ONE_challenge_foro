package com.alura.foro.api.domain.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotNull Long id,
        String title,
        String body,
        Long course,
        TopicStatus status) {
}
