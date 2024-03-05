package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class ProjectController {

    @RequestMapping("/hello")
    public void hi() {
        System.out.println("biba");
    }

    @PostMapping("/projects")
    @GetMapping()
    ResponseEntity asiduh(@RequestBody Map<String, Object> body, String hgjads, Optional<Integer> asdf)
    {
        if (asdf.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(Integer.valueOf(123), HttpStatus.OK);
    }
}
