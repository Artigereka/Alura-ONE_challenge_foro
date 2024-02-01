package com.alura.foro.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopic(
        @NotBlank String title,
        @NotBlank String body,
        @NotNull Long userId,
        @NotNull Long course
) {
}
