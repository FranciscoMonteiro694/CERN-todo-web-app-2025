package ch.cern.todo.controller;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.request.TaskCategoryRequest;
import ch.cern.todo.service.TaskCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @PostMapping
    public ResponseEntity<TaskCategory> createTaskCategory(@Valid @RequestBody TaskCategoryRequest taskCategoryRequest) {
        final TaskCategory taskCategory = taskCategoryService.createTaskCategory(taskCategoryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskCategory);
    }

    @GetMapping
    public ResponseEntity<List<TaskCategory>> retrieveAllCategories() {
        final List<TaskCategory> taskCategories = taskCategoryService.getAllCategories();
        return ResponseEntity.ok(taskCategories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<TaskCategory> getTaskCategoryById(@PathVariable Long categoryId) {
        final TaskCategory taskCategory = taskCategoryService.getTaskCategoryById(categoryId);
        return ResponseEntity.ok(taskCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable Long categoryId, @Valid @RequestBody TaskCategoryRequest taskCategoryRequest) {
        final TaskCategory updatedTaskCategory = taskCategoryService.updateTaskCategory(taskCategoryRequest, categoryId);
        return ResponseEntity.ok(updatedTaskCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteTaskCategory(@PathVariable Long categoryId) {
        taskCategoryService.deleteTaskCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
