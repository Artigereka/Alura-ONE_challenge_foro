package com.alura.foro.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    Boolean existsByTitleAndBody(String title, String body);

}
