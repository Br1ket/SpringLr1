package com.example.demo.pojo;

import com.example.demo.Entity.Project;
import com.example.demo.Entity.Task;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ProjectPojo {

    private long id;
    private String description;
    private String name;

    private LocalDate begin;
    private LocalDate end;
    private List<TaskPojo> tasks;

    public static ProjectPojo fromEntity(Project project) {
        ProjectPojo pojo = new ProjectPojo();
        pojo.setId(project.getId());
        pojo.setDescription(project.getDescription());
        pojo.setName(project.getName());
        pojo.setBegin(project.getBegin());
        pojo.setEnd(project.getBegin());

        if(project.getTasks() != null) {
            List<TaskPojo> tasks = project.getTasks().stream().map(TaskPojo::fromEntity).toList();
            pojo.setTasks(tasks);
        }

        return pojo;
    }

    public static Project toEntity(ProjectPojo pojo) {
        Project project = new Project();
        project.setId(pojo.getId());
        project.setName(pojo.getName());
        project.setDescription(pojo.getDescription());
        project.setBegin(pojo.getBegin());
        project.setEnd(pojo.getEnd());

        if(pojo.tasks != null) {
            List<Task> tasks = pojo.getTasks().stream().map(TaskPojo::toEntity).toList();
            project.setTasks(tasks);
        }

        return project;
    }
}
