package com.margub.splitwise.entities;

public class User {
    private Long id;
    private String name;

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
