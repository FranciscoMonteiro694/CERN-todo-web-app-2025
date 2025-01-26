package ch.cern.todo.service;

import ch.cern.todo.exceptions.TaskCategoryAlreadyExistsException;
import ch.cern.todo.exceptions.TaskCategoryConflictException;
import ch.cern.todo.exceptions.TaskCategoryDoesNotExistException;
import ch.cern.todo.model.Task;
import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.request.TaskCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    public TaskCategory createTaskCategory(TaskCategoryRequest taskCategoryRequest) {
        if (taskCategoryRepository.existsByCategoryName(taskCategoryRequest.categoryName())) {
            throw new TaskCategoryAlreadyExistsException("Task category with name '" + taskCategoryRequest.categoryName() + "' already exists.");
        }
        final TaskCategory newTaskCategory = new TaskCategory(taskCategoryRequest.categoryName(), taskCategoryRequest.categoryDescription());
        return taskCategoryRepository.save(newTaskCategory);
    }

    public List<TaskCategory> getAllCategories() {
        return taskCategoryRepository.findAll();
    }

    public TaskCategory getTaskCategoryById(Long categoryId) {
        return taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new TaskCategoryDoesNotExistException("Task category with the given ID '" + categoryId + "' does not exist."));
    }

    public TaskCategory updateTaskCategory(TaskCategoryRequest taskCategoryRequest, Long categoryId) {

        final TaskCategory categoryToUpdate = taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new TaskCategoryDoesNotExistException("Task category with the given ID '" + categoryId + "' does not exist."));

        final String newCategoryName = taskCategoryRequest.categoryName();
        if (!categoryToUpdate.getCategoryName().equals(newCategoryName) && taskCategoryRepository.existsByCategoryName(newCategoryName)) {
            throw new TaskCategoryAlreadyExistsException("Task category with name '" + newCategoryName + "' already exists.");
        }

        categoryToUpdate.setCategoryName(newCategoryName);
        categoryToUpdate.setCategoryDescription(taskCategoryRequest.categoryDescription());

        return taskCategoryRepository.save(categoryToUpdate);

    }

    public void deleteTaskCategory(Long categoryId) {
        if (!taskCategoryRepository.existsById(categoryId)) {
            throw new TaskCategoryDoesNotExistException("Task category with the given ID '" + categoryId + "' does not exist.");
        }

        // Check if there are tasks associated with the category
        List<Task> tasks = taskRepository.findByCategoryId(categoryId);
        if (!tasks.isEmpty()) {
            throw new TaskCategoryConflictException("Cannot delete task category with ID'" + categoryId + "' because it has associated tasks.");
        }

        taskCategoryRepository.deleteById(categoryId);
    }
}
