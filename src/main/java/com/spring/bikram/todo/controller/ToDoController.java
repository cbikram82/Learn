package com.spring.bikram.todo.controller;

import com.spring.bikram.todo.entity.ToDo;
import com.spring.bikram.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping
    public List<ToDo> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public ToDo createTask(@RequestBody ToDo task) {
        return service.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
    @PatchMapping("/{id}")
    public ToDo updateTaskStatus(@PathVariable Long id, @RequestParam boolean completed) {
        return service.updateTaskStatus(id, completed);
    }

}

