package com.todoapp.leanix.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.leanix.bean.Todo;
import com.todoapp.leanix.services.TodoService;
import com.todoapp.leanix.util.CustomErrorType;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/todos")
public class TodoController {

	public static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoService todoService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{id}")
	public ResponseEntity<?> getTodotask(@PathVariable("id") Long todoId) {
		logger.info("TodoController:getTodotask****Fetching Item with id {}******", todoId);
		Todo todoDetail = todoService.getTodo(todoId);
		if (null == todoDetail) {
			logger.error("itemDetail  with id {} not found.", todoDetail);
			return new ResponseEntity(
					new com.todoapp.leanix.util.CustomErrorType("todo with id " + todoId + " not found"),
					HttpStatus.NOT_FOUND);
		}
		logger.info("todoDetail  with id {} is.", todoDetail);
		return new ResponseEntity<Todo>(todoDetail, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addtodo(@RequestBody Todo todo) throws Exception {
		logger.info("TodoController:addtodo******" + todo.toString());
		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(todo);
			// Displaying JSON String
			logger.info(">>>>>>>>>>>>>>>>>>>>>>.." + jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		Todo todoDetail = null;
		try {
			todoDetail = todoService.saveTodo(todo);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new Exception(e);
		}
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<?> getAlltodo() throws Exception {
		logger.info("TodoController:getAlltodo******");
		List<Todo> todoList = todoService.getTodolist();

		if (todoList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/{id}")
	public ResponseEntity<?> upadteTodo(@PathVariable("id") Long todoId) throws Exception {

		logger.info("TodoController::::Updating item  with id {}", todoId);

		Todo todoDetail = todoService.getTodo(todoId);

		if (todoDetail == null) {
			logger.error("Unable to update. Todo with id {} not found.", todoId);
			return new ResponseEntity(new CustomErrorType("Unable to upate. todo with id " + todoId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		todoService.saveTodo(todoDetail);
		return new ResponseEntity<Todo>(todoDetail, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") long todoId) {
		logger.info("Fetching & Deleting Todo with id {}", todoId);

		Todo todoDetail = todoService.getTodo(todoId);

		if (todoDetail == null) {
			logger.error("Unable to update. Todo with id {} not found.", todoId);
			return new ResponseEntity(new CustomErrorType("Unable to delete. todo with id " + todoId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		todoService.deleteTodo(todoId);
		return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
	}

}
