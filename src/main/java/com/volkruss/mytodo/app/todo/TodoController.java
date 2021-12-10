package com.volkruss.mytodo.app.todo;

import java.util.Collection;
import java.util.Objects;

import javax.validation.groups.Default;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.volkruss.mytodo.app.todo.TodoForm.TodoCreate;
import com.volkruss.mytodo.app.todo.TodoForm.TodoDelete;
import com.volkruss.mytodo.app.todo.TodoForm.TodoFinish;
import com.volkruss.mytodo.domain.model.Todo;
import com.volkruss.mytodo.domain.service.todo.TodoService;

@Controller
// 全てのパスはtodo以下になる
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@GetMapping("/list")
	public String list(Model model) {
		Collection<Todo> todos = todoService.findAll();

		// フラッシュメッセージを取得する
		String resultMessage = (String) model.getAttribute("result");
		if(Objects.nonNull(resultMessage)) {
			model.addAttribute("result",resultMessage);
		}
		
		model.addAttribute("todos", todos);
		return "todo/list";
	}
	
	@PostMapping("/create")
	public String create(@Validated({Default.class,TodoCreate.class}) @ModelAttribute("todoForm") TodoForm todoForm, BindingResult bindingResult,Model model, RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return list(model);
		}
		ModelMapper mapper = new ModelMapper();
		Todo todo = mapper.map(todoForm, Todo.class);
		
		todoService.create(todo);
		
		//resultというkeyでフラッシュメッセージを設定する
		attributes.addFlashAttribute("result","Created successfully!");
		//リダイレクトする
		return "redirect:/todo/list";
	}
	
	@PostMapping("/finish")
	public String finish(
			@Validated({Default.class,TodoFinish.class}) TodoForm form,BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {
		if(bindingResult.hasErrors()) {
			return list(model);
		}
		todoService.finish(form.getTodoId());
		
		//resultというkeyでフラッシュメッセージを設定する
		attributes.addFlashAttribute("result","Finished  successfully!");
		//リダイレクトする
		return "redirect:/todo/list";
	}
	
	@PostMapping("delete")
	public String delete(
		@Validated({Default.class,TodoDelete.class}) TodoForm form,
		BindingResult bindingResult, Model model,
		RedirectAttributes attributes
			) {
		
		if(bindingResult.hasErrors()) {
			return list(model);
		}
		
		todoService.delete(form.getTodoId());
		
		//resultというkeyでフラッシュメッセージを設定する
		attributes.addFlashAttribute("result","Deleted   successfully!");
		//リダイレクトする
		return "redirect:/todo/list";	
	}
	
	
}
