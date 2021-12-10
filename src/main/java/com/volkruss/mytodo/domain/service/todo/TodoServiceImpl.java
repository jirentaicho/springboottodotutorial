package com.volkruss.mytodo.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volkruss.mytodo.domain.model.Todo;
import com.volkruss.mytodo.domain.repository.todo.TodoRepository;

@Service
// 公開メソッドをすべてトランザクション管理する
@Transactional
public class TodoServiceImpl implements TodoService {

	private static final long MAX_UNFINISHED_COUNT = 5;
	
	@Autowired
	private TodoRepository todoRepositroy;
	
	@Override
	public Collection<Todo> findAll() {
		return todoRepositroy.findAll();
	}

	@Override
	public Todo create(Todo todo) {

		String todoId = UUID.randomUUID().toString();
		Date createdAt = new Date();
		todo.setTodoId(todoId);
		todo.setCreatedAt(createdAt);
		todo.setFinished(false);
		
		todoRepositroy.save(todo);
		
		return todo;	
	}

	@Override
	public Todo finish(String todoId) {
		Todo todo = findOne(todoId);
		todo.setFinished(true);
		todoRepositroy.save(todo);
		return todo;
	}

	@Override
	public void delete(String todoId) {
		Todo todo = findOne(todoId);
		todoRepositroy.delete(todo);
	}
	
	@Override
	public boolean vaild() {
		long unfinishedCount = todoRepositroy.countByFinished(false);
		if(unfinishedCount >= MAX_UNFINISHED_COUNT) {
			return false;
		}
		return true;		
	}

	private Todo findOne(String taskId) {
		Optional<Todo> todo = todoRepositroy.findById(taskId);
		return todo.orElseThrow();
	}

}
