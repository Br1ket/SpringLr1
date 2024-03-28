package com.example.demo.Controller;

import com.example.demo.Model.Project;
import com.example.demo.Model.ProjectDto;
import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Создание проекта
    @PostMapping
    ResponseEntity<Object> createProject(@RequestBody ProjectDto rb) {

        if (!rb.getBegin().isBefore(rb.getEnd()))
            return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);

        Optional<ProjectDto> optionalProject = this.projectService.create(
                rb.getName(),
                rb.getDescription(),
                rb.getBegin(),
                rb.getEnd()
        );

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(optionalProject.get(), HttpStatus.valueOf(201));
    }

    @GetMapping("/{projectId}")
    ResponseEntity<Object> getProject(@PathVariable(required = true, name = "projectId") Long id) {
        Optional<ProjectDto> optionalProject = projectService.getById(id);

        if (optionalProject.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        System.out.println(optionalProject.get());
        return new ResponseEntity<>(optionalProject.get(), HttpStatus.OK);
    }


    @DeleteMapping("/{projectId}")
    ResponseEntity<Object> deleteProject(@PathVariable(required = true, name = "projectId") Long id) {
        if (projectService.delete(id) == 1) {
            System.out.println("Delete project with id = " + id + ";)");
        } else System.out.println("ERROR DELETE");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Получение проектов
    @GetMapping
    ResponseEntity<Object> getProjectFiltered(@RequestParam(name = "start_date") LocalDate start_date, @RequestParam(name = "end_date") LocalDate end_date) {
        Set<ProjectDto> setProjects = projectService.getByRange(start_date, end_date);
        if (setProjects.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (ProjectDto proj : setProjects) {
            System.out.println(proj);
        }

        return new ResponseEntity<>(setProjects, HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    ResponseEntity<Object> updateProject(@PathVariable(required = true, name = "projectId") Long id, @RequestBody ProjectDto p) {

        Optional<ProjectDto> optionalProject = projectService.getById(id);

        if (optionalProject.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        if (projectService.update(p,optionalProject.get())) {
            return new ResponseEntity<>(projectService.getById(id).get(),HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    ResponseEntity<Object> getAllProjects() {
        Set<ProjectDto> projectsSet = projectService.getAllProject();

        if (projectsSet.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (ProjectDto proj : projectsSet) {
            System.out.println(proj);
        }

        return new ResponseEntity<>(projectsSet, HttpStatus.OK);
    }


}

