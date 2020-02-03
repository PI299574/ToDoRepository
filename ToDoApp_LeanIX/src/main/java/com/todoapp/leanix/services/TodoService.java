package com.todoapp.leanix.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.leanix.bean.Todo;
import com.todoapp.leanix.repository.TodoRepository;

@Service("tododService")
public class TodoService {

	@Autowired
	private TodoRepository todoRep;
	public static final Logger logger = LoggerFactory.getLogger(TodoService.class);

	public Todo saveTodo(Todo todo) {
		logger.info("In TodoService:Savetodo *****");
		return todoRep.save(todo);

	}

	public Todo getTodo(long todoid) {
		logger.info("In TodoService:getTodo *****");
		return todoRep.findById(todoid).get();

	}

	public List<Todo> getTodolist() {
		logger.info("In TodoService:getTodoList *****");
		return (List<Todo>) todoRep.findAll();
	}

	public Todo updateTodo(long todoId) {
		logger.info("In TodoService:updateTodo *****");
		Todo todo = todoRep.findById(todoId).get();
		if (null != todo) {
			todoRep.save(todo);
		}
		return todo;
	}

	public void deleteTodo(long todoId) {
		logger.info("In TodoService:deleteTodo *****");
		todoRep.deleteById(todoId);

	}
}
