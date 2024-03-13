package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.Repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    //    Создание проекта.
    //    POST /projects
    //    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.

    public Optional<Project> create(String name, String description, LocalDate begin, LocalDate end) {
        return projectRepository.create(name,  description,  begin,  end);
    }

    public Optional<Project> getById(Long id) {
        return projectRepository.getById(id);
    }


    public boolean update(Project project) {
        return projectRepository.update(project);
    }


    public int delete(Long id) {
        return projectRepository.delete(id);
    }


    public Set<Project> getByRange(LocalDate begin, LocalDate end) {
        return projectRepository.getByRange(begin, end);
    }


    public Set<Project> getAllProject() {
       return projectRepository.getAllProject();
    }
}
