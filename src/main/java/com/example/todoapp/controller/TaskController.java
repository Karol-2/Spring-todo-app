package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RepositoryRestController
public class TaskController {
    private final TaskRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    TaskController(final TaskRepository repository){
        this.repository = repository;
    }
    @GetMapping (value = "/tasks", params = {"!sort","!page","!size"}) // for every GET on /tasks (without sort, page, size params) it will log and run findAll
    ResponseEntity<List<Task>> readAllTasks(){
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping ("/tasks") // for every GET on /tasks it will use this controller ex. /tasks?page=0&size=2
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
}
