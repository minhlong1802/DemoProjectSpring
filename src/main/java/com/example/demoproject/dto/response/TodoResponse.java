package com.example.demoproject.dto.response;

import java.time.LocalDate;

public class TodoResponse {
    private Integer id;
    private String task;
    private LocalDate dueDate;
    private String ldap;

    public TodoResponse(Integer id, String task, LocalDate dueDate, String ldap) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
        this.ldap = ldap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getLdap() {
        return ldap;
    }

    public void setLdap(String ldap) {
        this.ldap = ldap;
    }
}
