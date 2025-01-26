package ch.cern.todo.request;

import jakarta.validation.constraints.Size;

public record TaskRequest(
        @Size(max = 100, message = "Task name cannot exceed 100 characters")
        String taskName,
        @Size(max = 500, message = "Task description cannot exceed 500 characters")
        String taskDescription,
        String deadline, Long categoryId) {

    @Override
    public String taskName() {
        return taskName;
    }

    @Override
    public String taskDescription() {
        return taskDescription;
    }

    @Override
    public String deadline() {
        return deadline;
    }

    @Override
    public Long categoryId() {
        return categoryId;
    }
}
