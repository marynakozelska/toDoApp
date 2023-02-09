package com.app.controllers;

import com.app.models.ToDoItem;
import com.app.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;

    @Autowired
    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAll() {
        List<ToDoItem> toDoItemList = toDoItemService.getAll();
        return new ResponseEntity<>(toDoItemList, HttpStatus.OK);
    }

//    TODO:
    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getById(@PathVariable int id) {
        ToDoItem toDoItem = toDoItemService.getById(id);
        return new ResponseEntity<>(toDoItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoItem> addToDo(@RequestBody ToDoItem toDoItem) {
//        if (bindingResult.hasErrors()) return "todo/index";

        ToDoItem newToDoItem = toDoItemService.addToDoItem(toDoItem);
        return new ResponseEntity<>(newToDoItem, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ToDoItem> update(@RequestBody ToDoItem toDoItem) {
        ToDoItem newToDoItem = toDoItemService.updateToDo(toDoItem);
        return new ResponseEntity<>(newToDoItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") int id) {
        toDoItemService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
