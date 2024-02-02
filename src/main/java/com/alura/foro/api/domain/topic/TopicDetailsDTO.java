package com.alura.foro.api.domain.topic;

public record TopicDetailsDTO(
        Long id,
        String title,
        String body,
        Long author,
        Long course) {

    public TopicDetailsDTO(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getAuthor(),
                topic.getCourse());
    }

}
