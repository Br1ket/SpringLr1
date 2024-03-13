package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.Model.ProjectDto;
import com.example.demo.Repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService implements ProjectServiceI{

    @Autowired
    private final ProjectRepository projectRepository;

    //    Создание проекта.
    //    POST /projects
    //    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.

    @Override
    public Optional<ProjectDto> create(String name, String description, LocalDate begin, LocalDate end) {
        Optional<Project> optionalProject = projectRepository.create(name,  description,  begin,  end);

        if (optionalProject.isEmpty()) return Optional.empty();
        else {
            Project p = optionalProject.get();
            return Optional.of(new ProjectDto(p.getId(),p.getName(),p.getDescription(),p.getBegin(),p.getEnd()));
        }
    }

    @Override
    public Optional<ProjectDto> getById(Long id) {
        Optional<Project> project = projectRepository.getById(id);

        if(project.isEmpty()) return Optional.empty();
        else {
            Project p = project.get();
            ProjectDto projectDto = new ProjectDto(p.getId(),p.getName(),p.getDescription(),p.getBegin(),p.getEnd());
            return Optional.of(projectDto);
        }
    }


    @Override
    public boolean update(ProjectDto project, ProjectDto pTemp) {
        Project p = project.clone();

        p.setId(pTemp.getId());
        System.out.println(p);
        System.out.println(pTemp);
        if (p.getName() == null) {
            p.setName(pTemp.getName());
        }
        if (p.getDescription() == null) {
            p.setDescription(pTemp.getDescription());
        }
        if (p.getBegin() == null) {
            p.setBegin(pTemp.getBegin());
        }
        if (p.getEnd() == null) {
            p.setEnd(pTemp.getEnd());
        }

        System.out.println(p);
        return projectRepository.update(p);
    }


    @Override
    public int delete(Long id) {
        return projectRepository.delete(id);
    }


    @Override
    public Set<ProjectDto> getByRange(LocalDate begin, LocalDate end) {
        Set<Project> projectSet = projectRepository.getByRange(begin, end);

        return projectSet.stream().map((Project p) -> {
            return new ProjectDto(p.getId(),p.getName(),p.getDescription(),p.getBegin(),p.getEnd());
        }).collect(Collectors.toSet());
    }


    @Override
    public Set<ProjectDto> getAllProject() {
       Set<Project> projectSet = projectRepository.getAllProject();

        return projectSet.stream().map((Project p) -> {
           return new ProjectDto(p.getId(),p.getName(),p.getDescription(),p.getBegin(),p.getEnd());}).collect(Collectors.toSet());
    }
}
