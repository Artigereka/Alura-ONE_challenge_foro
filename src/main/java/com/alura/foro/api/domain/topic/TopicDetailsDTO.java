package com.alura.foro.api.domain.topic;

import java.time.LocalDateTime;

import com.alura.foro.api.domain.course.Category;

public record TopicDetailsDTO(
        Long id,
        String title,
        String body,
        String user,
        String course,
        Category courseCategory,
        TopicStatus status,
        LocalDateTime creationDate,
        LocalDateTime lastUpdated) {

    public TopicDetailsDTO(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getUser().getUsername(),
                topic.getCourse().getName(),
                topic.getCourse().getCategory(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated());
    }

}
