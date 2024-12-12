package com.example.demoproject.dto.response;

public class TaskCountResponse {
    private String name;
    private Long taskCount;

    public TaskCountResponse(String name, Long taskCount) {
        this.name = name;
        this.taskCount = taskCount;
    }

    public Long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Long taskCount) {
        this.taskCount = taskCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
