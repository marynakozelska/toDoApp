package com.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "to_do")
public class ToDoItem {
    @Id @GeneratedValue
    private int id;
    @Column
    private String content;
    @Column
    private String description;

    public ToDoItem(String content, String description) {
        this.content = content;
        this.description = description;
    }

    public ToDoItem() {
        System.out.println("ToDoItem is initialized");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
