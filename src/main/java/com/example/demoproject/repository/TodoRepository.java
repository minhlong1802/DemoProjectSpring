package com.example.demoproject.repository;

import com.example.demoproject.dto.response.TaskCountResponse;
import com.example.demoproject.dto.response.TodoResponse;
import com.example.demoproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    //Method to get all the tasks assigned to a user
    @Query("SELECT new com.example.demoproject.dto.response.TodoResponse(t.id, t.task, t.dueDate, u.ldap) " +
            "FROM Todo t JOIN t.user u WHERE u.name = :name")
    List<TodoResponse> findByUserName(@Param("name") String name);

    //Method to get number of tasks assigning to a user (group by username)
    @Query("SELECT new com.example.demoproject.dto.response.TaskCountResponse(u.name, COUNT(t.task)) " +
            "FROM Todo t JOIN t.user u GROUP BY u.name")
    List<TaskCountResponse> countTaskByUser();
}
