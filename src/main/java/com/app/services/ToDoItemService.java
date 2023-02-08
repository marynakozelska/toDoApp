package com.app.services;

import com.app.models.ToDoItem;
import com.app.repositories.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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

    public void deleteToDo(int id) {
        toDoItemRepository.deleteById(id);
    }

    public void updateToDo(ToDoItem inputToDoItem) {
        ToDoItem temporaryToDoItem = toDoItemRepository.getById(inputToDoItem.getId());

        temporaryToDoItem.setContent(inputToDoItem.getContent());
        temporaryToDoItem.setDescription(inputToDoItem.getDescription());

        toDoItemRepository.save(temporaryToDoItem);
    }
}
