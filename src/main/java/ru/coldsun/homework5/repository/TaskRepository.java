package ru.coldsun.homework5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coldsun.homework5.model.Task;
import ru.coldsun.homework5.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}
