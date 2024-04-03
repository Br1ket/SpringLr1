package com.example.demo.pojo;

import com.example.demo.Entity.Project;
import com.example.demo.Entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TaskPojo {

    private long id;
    private String description;
    private String name;

    private Date end;

    private boolean isfinished;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proj_id", nullable = false)
    @JsonIgnore
    private Project project;

    public static TaskPojo fromEntity(Task task) {
        TaskPojo pojo = new TaskPojo();

        pojo.setId(task.getId());
        pojo.setDescription(task.getDescription());
        pojo.setName(task.getName());
        pojo.setEnd(task.getEnd());
        pojo.setIsfinished(task.isIsfinished());
        pojo.setProject(task.getProject());

        return pojo;
    }

    public static Task toEntity(TaskPojo pojo) {
        Task task = new Task();

        task.setId(pojo.getId());
        task.setName(pojo.getName());
        task.setDescription(pojo.getDescription());
        task.setIsfinished(pojo.isIsfinished());
        task.setEnd(pojo.getEnd());
        task.setProject(pojo.getProject());

        return task;
    }
}
