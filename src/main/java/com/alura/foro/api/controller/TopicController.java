package com.alura.foro.api.controller;

import com.alura.foro.api.domain.topic.CreateTopicDTO;
import com.alura.foro.api.domain.topic.TopicStatus;
import com.alura.foro.api.domain.topic.Topic;
import com.alura.foro.api.domain.topic.TopicDetailsDTO;
import com.alura.foro.api.domain.topic.TopicRepository;
import com.alura.foro.api.domain.topic.UpdateTopicDTO;
import com.alura.foro.api.domain.topic.validators.create.TopicValidService;
import com.alura.foro.api.domain.user.UserRepository;
import com.alura.foro.api.domain.course.Course;
import com.alura.foro.api.domain.course.CourseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topics")//Name of the table
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    List<TopicValidService> validTopic;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDetailsDTO> createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO, UriComponentsBuilder uriBuilder){

        validTopic.forEach(v -> v.isDuplicated(createTopicDTO));
        var user = userRepository.findById(createTopicDTO.userID()).get();
        var course = courseRepository.findById(createTopicDTO.courseID()).get();
        var topic = new Topic(createTopicDTO, user, course);
        topicRepository.save(topic);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsDTO(topic));

    }

    @GetMapping("/all")
    public ResponseEntity<Page<TopicDetailsDTO>> readAllTopics(
            @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pagination){

        var page = topicRepository.findAll(pagination).map(TopicDetailsDTO::new);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> readOneTopic(@PathVariable Long id){

        Topic topic = topicRepository.getReferenceById(id);

        var topicData = new TopicDetailsDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getUser().getUsername(),
                topic.getCourse().getName(),
                topic.getCourse().getCategory(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated()
        );

        return  ResponseEntity.ok(topicData);

    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailsDTO>> readNonDeletedTopics(@PageableDefault(size = 5, sort = {"lastUpdated"},
            direction = Direction.DESC) Pageable pagination) {
        var page = topicRepository.findAllByStatus(TopicStatus.DELETED,pagination).map(TopicDetailsDTO::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDetailsDTO> updateTopic(@RequestBody @Valid UpdateTopicDTO updateTopicDTO,
                                                       @PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.updateTopic(updateTopicDTO);
        var topicData = new TopicDetailsDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getBody(),
                topic.getUser().getUsername(),
                topic.getCourse().getName(),
                topic.getCourse().getCategory(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastUpdated()
        );
        return ResponseEntity.ok(topicData);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deleteTopic();
        return ResponseEntity.noContent().build();
    }

}
