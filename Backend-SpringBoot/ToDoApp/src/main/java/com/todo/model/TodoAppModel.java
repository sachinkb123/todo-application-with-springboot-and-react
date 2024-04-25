package com.todo.model;

import java.time.LocalDate;

import com.todo.domain.Priority;

import lombok.Data;

@Data
public class TodoAppModel {
	
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private Priority priority;
	private boolean completed;

}
