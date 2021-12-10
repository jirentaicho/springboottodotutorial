package com.volkruss.mytodo.domain.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volkruss.mytodo.domain.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, String>{

}
