package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.todo.domain.TodoAppDomain;
import com.todo.service.TodoAppService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoAppController {
	
	@Autowired
	private TodoAppService todoAppService;
	
	@PostMapping("/addTasks")
    public ResponseEntity<TodoAppDomain> saveTodoTask(@RequestBody TodoAppDomain todoAppDomain) throws Exception {
        TodoAppDomain savedTodo = todoAppService.saveTask(todoAppDomain);
        return ResponseEntity.ok(savedTodo);
    }
	
	@GetMapping("/getAllTasks")
	public ResponseEntity<List<TodoAppDomain>> getAllTasks() throws Exception{
		List<TodoAppDomain> getTaskDetails = todoAppService.getAllTasks();
		return ResponseEntity.ok(getTaskDetails);
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable("id") int id) throws Exception {
	    try {
	        String deleteTaskByName = todoAppService.deleteTask(id);
	        return ResponseEntity.ok(deleteTaskByName);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting task: " + e.getMessage());
	    }
	}
	
	@PutMapping("updateTasks/{id}")
	public ResponseEntity<TodoAppDomain> updateTask(@PathVariable long id) throws Exception{
		 TodoAppDomain updatedTask = todoAppService.updateTasks(id);
	        return ResponseEntity.ok(updatedTask);
	}
}
