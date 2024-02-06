package com.alura.foro.api.controller;

import com.alura.foro.api.domain.topic.*;
import com.alura.foro.api.domain.topic.validators.create.TopicValidService;
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

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> readOneTopic(@PathVariable Long id){

        Topic topic = topicRepository.getReferenceById(id);

        var topicData = new TopicDetailsDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated()
        );

        return  ResponseEntity.ok(topicData);

    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailsDTO>> readNonArchivedTopics(@PageableDefault(
            size = 5, sort = {"id"}) Pageable pagination) {

        var page = topicRepository.findAllByStatus(TopicStatus.ARCHIVED,pagination).map(TopicDetailsDTO::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid UpdateTopicDTO updateTopicDTO) {
        Topic topic = topicRepository.getReferenceById(updateTopicDTO.id());
        topic.updateTopic(updateTopicDTO);
        var topicData = new TopicDetailsDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated()
        );
        return ResponseEntity.ok(topicData);

    }

}
