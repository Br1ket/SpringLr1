package com.example.demo.Service;

import com.example.demo.Entity.Project;
import com.example.demo.Entity.Task;
import com.example.demo.Repository.ProjectRepositoryI;
import com.example.demo.Repository.TaskRepositoryI;
import com.example.demo.pojo.TaskPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServiceI{

    @Autowired
    private TaskRepositoryI taskRepository;

    @Autowired
    private ProjectRepositoryI projectRepositoryI;

    @Override
    public Optional<TaskPojo> createTaskByProjectId(TaskPojo task, int id) {
        Task tas = TaskPojo.toEntity(task);
        Project project = projectRepositoryI.findById(id).get();
        tas.setProject(project);

        return Optional.of(TaskPojo.fromEntity(taskRepository.save(tas)));
    }

    @Override
    public Optional<TaskPojo> getTaskByProjectIdAndTaskId(int tId, int pId) {
        return Optional.of(TaskPojo.fromEntity(taskRepository.findByIdAndProjectId(tId,pId)));
    }

    @Override
    public Optional<TaskPojo> updateTaskByProjectIdAndTaskId(int tId, int pId, TaskPojo newTask) {
        Task oldTask = taskRepository.findByIdAndProjectId(tId,pId);

        if(newTask.getName() != null) {
            oldTask.setName(newTask.getName());
        }
        if(newTask.getDescription() != null) {
            oldTask.setDescription(newTask.getDescription());
        }
        if(newTask.getEnd() != null) {
            oldTask.setEnd(newTask.getEnd());
        }
        if(newTask.isIsfinished() != oldTask.isIsfinished()) {
            oldTask.setIsfinished(!oldTask.isIsfinished());
        }

        return Optional.of(TaskPojo.fromEntity(taskRepository.save(oldTask)));
    }

    @Override
    public void deleteTask(int tId, int pId) {
        taskRepository.deleteByIdAndProjectId(tId, pId);
    }

    @Override
    public void deleteTasks(int pId) {
        taskRepository.deleteByProjectIdAndIsfinishedTrue(pId);
    }


    @Override
    public List<TaskPojo> getAllTasksByProject(int id) {
        List<Task> tasks = taskRepository.findAllByProjectId(id);

        return tasks.stream().map(TaskPojo::fromEntity).toList();
    }



}
