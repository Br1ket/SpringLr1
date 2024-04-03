package com.example.demo.Controller;

import com.example.demo.Service.TaskService;
import com.example.demo.pojo.TaskPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<?> getAllProjects(@PathVariable(required = true, name = "projectId") Long id) {
        List<TaskPojo> tasks = taskService.getAllTasksByProject(Math.toIntExact(id));

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskPojo taskPojo, @PathVariable(required = true, name = "projectId") Long id) {
        System.out.println(taskPojo);
        Optional<TaskPojo> pojo = taskService.createTaskByProjectId(taskPojo, Math.toIntExact(id));

        if (pojo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(pojo.get(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskByTaskIdAndProjectId(@PathVariable(required = true, name = "projectId") Long projectId,
                                                         @PathVariable(required = true, name = "taskId") Long taskId) {
        Optional<TaskPojo> taskPojo = taskService.getTaskByProjectIdAndTaskId(Math.toIntExact(taskId), Math.toIntExact(projectId));

        if (taskPojo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(taskPojo.get(), HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTaskByTaskIdAndProjectId(@PathVariable(required = true, name = "projectId") Long projectId,
                                                            @PathVariable(required = true, name = "taskId") Long taskId,
                                                            @RequestBody TaskPojo task) {

        Optional<TaskPojo> taskPojo = taskService.updateTaskByProjectIdAndTaskId(Math.toIntExact(taskId), Math.toIntExact(projectId), task);

        if (taskPojo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(taskPojo.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable(required = true, name = "projectId") Long projectId,
                                        @PathVariable(required = true, name = "taskId") Long taskId) {
        taskService.deleteTask(Math.toIntExact(taskId), Math.toIntExact(projectId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> deleteTasks(@PathVariable(required = true, name = "projectId") Long projectId) {
        taskService.deleteTasks(Math.toIntExact(projectId));

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
