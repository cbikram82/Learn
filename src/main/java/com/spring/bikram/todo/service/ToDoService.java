package com.spring.bikram.todo.service;


import com.spring.bikram.todo.entity.ToDo;
import com.spring.bikram.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository repository;

    public List<ToDo> getAllTasks() {
        return repository.findAll();
    }

    public ToDo saveTask(ToDo task) {
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public ToDo updateTaskStatus(Long id, boolean completed) {
        ToDo task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(completed);
        return repository.save(task);
    }

}

