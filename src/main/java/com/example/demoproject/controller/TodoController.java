package com.example.demoproject.controller;

import com.example.demoproject.dto.response.TaskCountResponse;
import com.example.demoproject.dto.response.TodoResponse;
import com.example.demoproject.entity.Todo;
import com.example.demoproject.service.TodoService;
import com.example.demoproject.dto.request.TodoCreationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Link call swagger
//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @Operation(summary = "Create a new todo", responses = {
            @ApiResponse(responseCode = "201", description = "Todo created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Todo createTodo(@RequestBody TodoCreationRequest request) {
        return todoService.createTodo(request);
    }

    @Operation(summary = "View a list of available todos", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved todos"),
            @ApiResponse(responseCode = "404", description = "No todos found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @Operation(summary = "Get a todo by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved todo"),
            @ApiResponse(responseCode = "400", description = "Todo not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{id}")
    public Todo getTodoById(@PathVariable Integer id) {
        return todoService.getTodoById(id);
    }

    @Operation(summary = "Update an existing todo", responses = {
            @ApiResponse(responseCode = "200", description = "Todo updated successfully"),
            @ApiResponse(responseCode = "400", description = "Todo not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody TodoCreationRequest request) {
        return todoService.updateTodo(id, request);
    }
    @Operation(summary = "Delete a todo by its ID", responses = {
            @ApiResponse(responseCode = "204", description = "Todo deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Todo not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteTodoById(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
    }
    //Method to get all todos by username
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/assignee/{name}")
    public List<TodoResponse> getTodosByAssigneeName(@PathVariable String name) {
        return todoService.getTodosByUserName(name);
    }
    //Method to get number of tasks assigning to a user (group by username)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/count")
    public List<TaskCountResponse> countTaskByUser() {
        return todoService.countTaskByUser();
    }

}
