package ch.cern.todo.repository;

import ch.cern.todo.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

    Optional<TaskCategory> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String s);
}
