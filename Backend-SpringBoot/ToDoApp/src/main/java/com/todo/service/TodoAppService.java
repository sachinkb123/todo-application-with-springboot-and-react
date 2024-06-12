package com.todo.service;

import java.util.List;

import com.todo.domain.TodoAppDomain;

public interface TodoAppService {

	public TodoAppDomain saveTask(TodoAppDomain todoAppDomain)throws Exception;

	public List<TodoAppDomain> getAllTasks()throws Exception;

	public String deleteTask(int id)throws Exception;

	public TodoAppDomain updateTasks(long id) throws Exception;

}
