package io.nology.todo_app.todopost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoPostRepository extends JpaRepository<TodoPost, Long> {
    @Query("SELECT t FROM TodoPost t WHERE t.completed = :completed ORDER BY t.position ASC")
    List<TodoPost> findByCompletedOrderByPosition(@Param("completed") boolean completed);
}