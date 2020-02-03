package com.todoapp.leanix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.leanix.bean.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
