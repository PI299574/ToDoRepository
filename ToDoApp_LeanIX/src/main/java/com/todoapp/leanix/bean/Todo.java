package com.todoapp.leanix.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.ToString;

@Table(name = "Todo")
@Entity
@ToString
public class Todo {

	public Todo() {
		// TODO Auto-generated constructor stub
	}

	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "todoId")
	@Id
	long todoId;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "todo_task", joinColumns = @JoinColumn(name = "todoId"), inverseJoinColumns = @JoinColumn(name = "taskid"))
	private List<Tasks> tasks;

	/**
	 * @return the todoId
	 */
	public long getTodoId() {
		return todoId;
	}

	/**
	 * @param todoId the todoId to set
	 */
	public void setTodoId(long todoId) {
		this.todoId = todoId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the tasks
	 */
	public List<Tasks> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

}
