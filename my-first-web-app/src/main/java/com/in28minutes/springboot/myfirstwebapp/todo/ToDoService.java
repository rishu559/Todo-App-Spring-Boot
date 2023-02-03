package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class ToDoService {
	
	private static List<ToDo> todos=new ArrayList<ToDo>();
	private static int todoCount=3;
	static {
		todos.add(new ToDo(1,"Rishu","Learn Java",LocalDate.now().plusDays(30),false));
		todos.add(new ToDo(2,"Rishu","Build Muscles",LocalDate.now().plusMonths(3),false));
		todos.add(new ToDo(3,"Rishu","Make 2 mini projects",LocalDate.now().plusMonths(2),false));
	}
	
	
	public List<ToDo> findByUsername(String username){
		Predicate<? super ToDo> predicate=todo->todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username,String description,LocalDate targetDate,boolean done){
		todos.add(new ToDo(++todoCount,username,description,targetDate,done));
	}
	
	public void deleteTodo(int id) {	
		Predicate<? super ToDo> predicate=todo->todo.getId()==id;
		//		for(ToDo td:todos) {
//			if(td.getId()==id) todos.remove(td);
//		}
		todos.removeIf(predicate);
	}
	
	public ToDo getById(int id) {
		Predicate<? super ToDo> predicate=todo->todo.getId()==id;
		//		for(ToDo td:todos)
//			if(td.getId()==id) return td;
		ToDo toDo = todos.stream().filter(predicate).findFirst().get();
		return toDo;
	}
	
	public void updateToDo(@Valid ToDo td) {
		for(int i=0;i<todos.size();i++) {
			if(td.getId()==todos.get(i).getId()) {
				todos.set(i,td);
			}
		}
	}
}
