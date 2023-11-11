package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "todos",collectionResourceRel = "todos")
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // block sending DELETE
    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task task);

    // seperate path for sql request
    @RestResource(path="done", rel = "done") // address: /tasks/search/done?state=true
    List<Task> findByDone(@Param("state") boolean done);

}
