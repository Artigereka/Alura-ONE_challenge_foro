package com.alura.foro.api.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private Long author;
    private Long course;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;

    public Topic(CreateTopicDTO createTopicDTO){
        this.title = createTopicDTO.title();
        this.body = createTopicDTO.body();
        this.author = createTopicDTO.author();
        this.course = createTopicDTO.course();
        this.status = TopicStatus.OPEN;
        this.creationDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    public void updateTopic(UpdateTopicDTO updateTopicDTO) {
        if (updateTopicDTO.title() != null) {
            this.title = updateTopicDTO.title();
        }
        if (updateTopicDTO.body() != null) {
            this.body = updateTopicDTO.body();
        }
        if (updateTopicDTO.status() != null) {
            this.status = updateTopicDTO.status();
        }
        if (updateTopicDTO.course() != null) {
            this.course = updateTopicDTO.course();
        }
        this.lastUpdated = LocalDateTime.now();
    }

    public void deleteTopic() {
        this.status = TopicStatus.DELETED;
    }

}
