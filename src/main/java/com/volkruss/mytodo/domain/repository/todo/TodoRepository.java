package com.volkruss.mytodo.domain.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.volkruss.mytodo.domain.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, String>{
	@Query("SELECT COUNT(t) FROM Todo t WHERE t.finished = :finished")
	public long countByFinished(boolean finished);
}
