package com.alura.foro.api.domain.topic;

import java.time.LocalDateTime;

public record TopicDetailsDTO(
        Long id,
        String title,
        String body,
        Long author,
        Long course,
        TopicStatus status,
        LocalDateTime creationDate,
        LocalDateTime lastUpdated) {

    public TopicDetailsDTO(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated());
    }

}
