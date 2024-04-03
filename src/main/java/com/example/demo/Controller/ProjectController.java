package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import com.example.demo.pojo.ProjectPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Создание проекта
    @PostMapping
    ResponseEntity<Object> createProject(@RequestBody ProjectPojo project) {

        if (!project.getBegin().isBefore(project.getEnd()))
            return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);

        Optional<ProjectPojo> optionalProject = projectService.create(project);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(optionalProject.get(), HttpStatus.valueOf(201));
    }

    @GetMapping("/{projectId}")
    ResponseEntity<Object> getProject(@PathVariable(required = true, name = "projectId") Long id) {
        Optional<ProjectPojo> optionalProject = projectService.getById(id);

        if (optionalProject.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        System.out.println(optionalProject.get());
        return new ResponseEntity<>(optionalProject.get(), HttpStatus.OK);
    }


    @DeleteMapping("/{projectId}")
    ResponseEntity<Object> deleteProject(@PathVariable(required = true, name = "projectId") Long id) {
        projectService.delete(id);

        if (projectService.getById(id).isEmpty()) {
            System.out.println("Delete project with id = " + id + ";)");
        } else System.out.println("ERROR DELETE");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{projectId}")
    ResponseEntity<Object> updateProject(@PathVariable(required = true, name = "projectId") Long id, @RequestBody ProjectPojo p) {

        Optional<ProjectPojo> optionalProject = projectService.getById(id);

        if (optionalProject.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        Optional<ProjectPojo> pojo = projectService.update(p,optionalProject.get());
        if(pojo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(projectService.getById(id).get(),HttpStatus.OK);

    }

    @GetMapping("/all")
    ResponseEntity<Object> getAllProjects() {
        List<ProjectPojo> projectsList = projectService.getAllProject();

        if (projectsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (ProjectPojo proj : projectsList) {
            System.out.println(proj);
        }

        return new ResponseEntity<>(projectsList, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProjectByDescFilter(@RequestParam("search") String phrase) {
        List<ProjectPojo> projects = projectService.getProjectByDescFilter(phrase);
        return new ResponseEntity<>(projects, projects == null || projects.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<?> getNotFulfilledTask() {
        return new ResponseEntity<>(projectService.getNotFulfilledTask(), HttpStatus.OK);
    }
}

