package com.app.services;

import com.app.models.ToDoItem;
import com.app.repositories.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public List<ToDoItem> getAll() {
        return toDoItemRepository.findAll();
    }

    public ToDoItem getById(int id) {
        return toDoItemRepository.getById(id);
    }

    public void addToDoItem(ToDoItem toDoItem) {
        toDoItemRepository.save(toDoItem);
    }
}
