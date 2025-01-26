package ch.cern.todo.repository;

import ch.cern.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTaskName(String s);

    @Query("SELECT t FROM Task t WHERE t.category.id = :categoryId")
    List<Task> findByCategoryId(@Param("categoryId") Long categoryId);
}
