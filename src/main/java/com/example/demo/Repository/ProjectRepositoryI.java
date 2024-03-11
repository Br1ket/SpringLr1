package com.example.demo.Repository;

import com.example.demo.Model.Project;

import java.util.Optional;

public interface ProjectRepositoryI {

    public Optional<Project> getById(Long id);

    public boolean delete(Long id);
}
