package com.alura.foro.api.controller;

import com.alura.foro.api.domain.topic.CreateTopicDTO;
import com.alura.foro.api.domain.topic.Topic;
import com.alura.foro.api.domain.topic.validators.create.TopicValidService;
import com.alura.foro.api.domain.topic.TopicDetailsDTO;
import com.alura.foro.api.domain.topic.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topics")//Name of the table
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    List<TopicValidService> validTopic;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO, UriComponentsBuilder uribBuilder){

        validTopic.forEach(v -> v.isDuplicated(createTopicDTO));
        var topic = new Topic(createTopicDTO);
        topicRepository.save(topic);

        var uri = uribBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsDTO(topic));

    }

    @GetMapping("/all")
    public ResponseEntity<Page<TopicDetailsDTO>> readAllTopics(
            @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pagination){

        return ResponseEntity.ok(topicRepository.findAll(pagination).map(TopicDetailsDTO::new));

    }

}
