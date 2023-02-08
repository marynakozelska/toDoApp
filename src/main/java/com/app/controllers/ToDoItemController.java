package com.app.controllers;

import com.app.models.ToDoItem;
import com.app.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;

    @Autowired
    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping
    public String getAll(@ModelAttribute("addItem") ToDoItem toDoItem, Model model) {
        model.addAttribute("items", toDoItemService.getAll());
        return "todo/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        model.addAttribute("item", toDoItemService.getById(id));
        return "todo/index-item";
    }

    @PostMapping
    public String addToDo(@ModelAttribute("addItem") ToDoItem toDoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "todo/index";

        toDoItemService.addToDoItem(toDoItem);
        return "redirect:/todo";
    }

    @PatchMapping("/save")
    public String saveToDo (@ModelAttribute("todo") ToDoItem toDoItem) {
       toDoItemService.updateToDo(toDoItem);
        return "redirect:/todo";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        ToDoItem toDoItem = toDoItemService.getById(id);
        model.addAttribute("item", toDoItem);
        return "todo/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteToDo(@PathVariable("id") int id) {
        toDoItemService.deleteToDo(id);
        return "redirect:/todo";
    }
}
