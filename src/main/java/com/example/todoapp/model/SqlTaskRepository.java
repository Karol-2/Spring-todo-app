package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTaskRepository extends JpaRepository<Task, Integer>, TaskRepository {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=id")
    boolean existsById(@Param("id")Integer id);


}
