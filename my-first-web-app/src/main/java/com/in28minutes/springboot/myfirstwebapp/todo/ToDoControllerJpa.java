package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoControllerJpa {
	
	private TodoRepository todoRepository;
		
	public ToDoControllerJpa( TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername();
//		List<ToDo> todos = todoservice.findByUsername(username);
		List<ToDo> todos = todoRepository.findByUsername(username);
		
		model.put("todos", todos);
		return "listTodos";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.GET)
	public String addTodo(ModelMap model) {
		String username = getLoggedinUsername();
		ToDo todo = new ToDo(0,username,"",LocalDate.now(),false);
		model.put("todo", todo);
		return "new-todo-page";
	}

//	private String getLoggedinUsername(ModelMap model) {
//		return (String)model.get("name");
//	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	public String addNewTodo(ModelMap model,@ModelAttribute("todo")@Valid ToDo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "new-todo-page"; 
		}
		String username = getLoggedinUsername();
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos"; // to redirect to the link
//		todoservice.addTodo(username, todo.getDescription(),todo.getTargetDate(),todo.isDone());
//		can not return lisTodos directly because it will give an empty page
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
//		todoservice.deleteTodo(id);
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/update-todo" ,method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
//		ToDo td = todoservice.getById(id);
		ToDo td = todoRepository.findById(id).get();
		model.put("todo",td);

		return "new-todo-page";
	}
	
	@RequestMapping(value="/update-todo" ,method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid ToDo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "new-todo-page"; 
		}
		
		todo.setUsername(getLoggedinUsername());
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
}
