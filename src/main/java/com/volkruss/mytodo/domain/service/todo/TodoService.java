package com.volkruss.mytodo.domain.service.todo;

import java.util.Collection;

import com.volkruss.mytodo.domain.model.Todo;

public interface TodoService {
	
	Collection<Todo> findAll();
	
	Todo create(Todo todo);
	
	Todo finish(String todoId);
	
	void delete(String todoId);
	
	boolean vaild();
	
}
