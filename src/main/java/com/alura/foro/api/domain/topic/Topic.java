package com.alura.foro.api.domain.topic;

import com.alura.foro.api.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Long course;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;

    public Topic(CreateTopicDTO createTopicDTO, User user){
        this.title = createTopicDTO.title();
        this.body = createTopicDTO.body();
        this.user = user;
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
