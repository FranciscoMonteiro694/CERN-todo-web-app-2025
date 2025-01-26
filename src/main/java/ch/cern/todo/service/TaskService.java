package ch.cern.todo.service;

import ch.cern.todo.exceptions.TaskCategoryDoesNotExistException;
import ch.cern.todo.exceptions.TaskDoesNotExistException;
import ch.cern.todo.model.Task;
import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.request.TaskRequest;
import ch.cern.todo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public Task createTask(TaskRequest taskRequest) {

        final LocalDateTime deadline = DateUtils.validateDeadline(taskRequest.deadline());

        final TaskCategory taskCategory = taskCategoryRepository.findById(taskRequest.categoryId())
                .orElseThrow(() -> new TaskCategoryDoesNotExistException("Task category with the given ID '" + taskRequest.categoryId() + "' does not exist."));

        final Task newTask = new Task(taskRequest.taskName(), taskRequest.taskDescription(), deadline, taskCategory);

        return taskRepository.save(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskDoesNotExistException("Task with the given ID '" + taskId + "' does not exist."));
    }

    public Task updateTask(TaskRequest taskRequest, Long taskId) {

        final LocalDateTime deadline = DateUtils.validateDeadline(taskRequest.deadline());

        final Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskDoesNotExistException("Task with the given ID '" + taskId + "' does not exist."));

        taskToUpdate.setTaskName(taskRequest.taskName());
        taskToUpdate.setTaskDescription(taskRequest.taskDescription());
        taskToUpdate.setDeadline(deadline);
        taskToUpdate.setCategory(taskCategoryRepository.findById(taskRequest.categoryId())
                .orElseThrow(() -> new TaskCategoryDoesNotExistException("Task category with the given ID '" + taskRequest.categoryId() + "' does not exist.")));

        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskDoesNotExistException("Task with the given ID '" + taskId + "' does not exist.");
        }
        taskRepository.deleteById(taskId);
    }

}
