package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.Repository.TodoAppRepository;
import com.todo.domain.TodoAppDomain;
import com.todo.exceptions.ResourceNotFoundException;

@Service
public class TodoAppServiceImpl implements TodoAppService {

	@Autowired
	private TodoAppRepository todoAppRepository;

	@Override
	public TodoAppDomain saveTask(TodoAppDomain todoAppDomain) throws Exception {
		return todoAppRepository.save(todoAppDomain);
	}

	@Override
	public List<TodoAppDomain> getAllTasks() throws Exception {
		return todoAppRepository.findAll();
	}

	@Override
	public String deleteTask(int id) throws Exception {
		todoAppRepository.deleteById(id);
		return "Task Deleted SuccessFully";
	}

	@Override
	public TodoAppDomain updateTasks(long id) throws Exception {
	  TodoAppDomain appDomain = todoAppRepository.findById(id);
	  if (appDomain == null) {
	    throw new ResourceNotFoundException("Task with id " + id + " not found");
	  }
	  appDomain.setCompleted(true);
	  todoAppRepository.save(appDomain);
	  return appDomain;
	}

}
