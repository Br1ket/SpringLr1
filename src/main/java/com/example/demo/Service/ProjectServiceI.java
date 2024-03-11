package com.example.demo.Service;

import com.example.demo.ProjectDto;

import java.util.Optional;

public interface ProjectServiceI {
    public Optional<ProjectDto> getById(Long id);

    public boolean delete(Long id);
}
