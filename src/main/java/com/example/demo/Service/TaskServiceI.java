package com.example.demo.Service;

import com.example.demo.Entity.Task;
import com.example.demo.pojo.TaskPojo;

import java.util.List;
import java.util.Optional;

public interface TaskServiceI {

    public List<TaskPojo> getAllTasksByProject(int id);

    public Optional<TaskPojo> createTaskByProjectId(TaskPojo task, int id);

    public Optional<TaskPojo> getTaskByProjectIdAndTaskId(int tId, int pId);

    public Optional<TaskPojo> updateTaskByProjectIdAndTaskId(int tId, int pId, TaskPojo task);

    public void deleteTask(int tId, int pId);

    public void deleteTasks(int pId);
}
