package com.example.demoproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
@NamedEntityGraph(name = "User.todos", attributeNodes = @NamedAttributeNode("todos"))
public class User {
    private String name;
    @Id
    private String userId;
    private String ldap;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Todo> todos;

    public User(){
    }

    public User(String name, String userId, String ldap){
        this.name=name;
        this.userId=userId;
        this.ldap=ldap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLdap() {
        return ldap;
    }

    public void setLdap(String ldap) {
        this.ldap = ldap;
    }
    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
