package com.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.domain.TodoAppDomain;

@SuppressWarnings("unchecked")
public interface TodoAppRepository extends JpaRepository<TodoAppDomain, Integer> {

	public TodoAppDomain save(TodoAppDomain todoAppDomain);

	public TodoAppDomain findById(long id);
}
