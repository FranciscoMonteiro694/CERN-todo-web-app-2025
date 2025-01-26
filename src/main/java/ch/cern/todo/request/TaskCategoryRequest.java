package ch.cern.todo.request;


import jakarta.validation.constraints.Size;

public record TaskCategoryRequest(
        @Size(max = 100, message = "Category name cannot exceed 100 characters")
        String categoryName,
        @Size(max = 100, message = "Category description cannot exceed 500 characters")
        String categoryDescription) {

    @Override
    public String categoryName() {
        return categoryName;
    }

    @Override
    public String categoryDescription() {
        return categoryDescription;
    }

}
