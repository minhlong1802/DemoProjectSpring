package com.example.demoproject.service;

import com.example.demoproject.dto.request.TodoCreationRequest;
import com.example.demoproject.dto.response.TaskCountResponse;
import com.example.demoproject.dto.response.TodoResponse;
import com.example.demoproject.entity.User;
import com.example.demoproject.exception.TodoValidationException;
import com.example.demoproject.entity.Todo;
import com.example.demoproject.repository.TodoRepository;
import com.example.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(TodoCreationRequest request){
        validateTodoRequest(request);
        Todo todo = new Todo();
        todo.setTask(request.getTask());
        todo.setDueDate(request.getDueDate());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            todo.setUser(user);
        } else {
            todo.setUser(null);
        }
        return todoRepository.save(todo);
    }
    private void validateTodoRequest(TodoCreationRequest request) {
        if (request.getTask() == null || request.getTask().isBlank()) {
            throw new TodoValidationException("Task cannot be empty");
        }

        if (request.getDueDate() == null || request.getDueDate().isBefore(LocalDate.now())) {
            throw new TodoValidationException("Due date must be today or in the future");
        }
    }
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    public Todo getTodoById(Integer id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoValidationException("Todo with id " + id + " not found"));
    }
    public Todo updateTodo(Integer id, TodoCreationRequest request) {
        validateTodoRequest(request);
        Todo todo = getTodoById(id);
        todo.setTask(request.getTask());
        todo.setDueDate(request.getDueDate());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            todo.setUser(user);
        } else {
            todo.setUser(null);
        }
        return todoRepository.save(todo);
    }
    public void deleteTodoById(Integer id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoValidationException("Todo with id " + id + " not found");
        }
        todoRepository.deleteById(id);
    }
    //Method to get all todos by username
    public List<TodoResponse> getTodosByUserName(String name) {
        return todoRepository.findByUserName(name);
    }
    //Method to get number of tasks assigning to a user (group by username)
    public List<TaskCountResponse> countTaskByUser() {
        return todoRepository.countTaskByUser();
    }
}
