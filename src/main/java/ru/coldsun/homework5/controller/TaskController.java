package ru.coldsun.homework5.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import ru.coldsun.homework5.model.Task;
import ru.coldsun.homework5.model.TaskStatus;
import ru.coldsun.homework5.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    //@Autowired
    private final TaskRepository taskRepository;

    /**
     * Добавление задачи.
     */
    @PostMapping
    public Task addTask(@RequestBody Task task){
        task.setCreationDate(LocalDateTime.now());
        if (task.getStatus()==null) task.setStatus(TaskStatus.NOT_STARTED);

        return taskRepository.save(task);
    }

    /**
     * Просмотр всех задач
     */
    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    /**
     * Просмотр задач по статусу
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Изменение статуса задачи
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        Task changeTask = taskRepository.findById(id).orElse(null);
        if (changeTask != null) {
            changeTask.setStatus(task.getStatus());
            return taskRepository.save(changeTask);
        } else {
            return null;
        }
    }

    /**
    * Удаление задачи
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }
}
