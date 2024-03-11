package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/hello")
    public void hi() {
        System.out.println("biba");
    }

    @GetMapping("/{projectId}")
    ResponseEntity getProject( @PathVariable(required = true, name = "projectId") Long id) {
        var dbo = projectService.getById(id);
        System.out.println(dbo);
        if (dbo.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(dbo.get(),HttpStatus.OK);
    }


    @DeleteMapping("/{projectId}")
    ResponseEntity deleteProject(
            @PathVariable(required = true, name = "projectId") Long id
    ) {
        if (projectService.delete(id)) {
            System.out.println("Delete project with id = " + id + "))");

        }
        else System.out.println("ERROR DELETE");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

   /* @PutMapping("/{projectId}")
    ResponseEntity updateProject(
            @PathVariable(required = true, name = "projectId") Long id,
            @RequestBody Map<String,String> rb
    ) {
        if (projectService.getById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional<String>    name = Optional.ofNullable(rb.get("name"));
        Optional<String>    description = Optional.ofNullable(rb.get("description"));
        Optional<LocalDate> begin = Optional.empty();
        Optional<LocalDate> end = Optional.empty();
        var begin_rv = rb.get("begin");
        if (begin_rv != null)
            begin = Optional.of(LocalDate.parse(begin_rv));
        var end_rv = rb.get("end");
        if (end_rv != null)
            end = Optional.of(LocalDate.parse(end_rv));
        if (name.isPresent())
            projectService.setName(id, name.get());
        if (description.isPresent())
            projectService.setDescription(id, description.get());
        if (begin.isPresent())
            projectService.setBegin(id, begin.get());
        if (end.isPresent())
            projectService.setEnd(id, end.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

}
