package com.example.demo.Repository;

import com.example.demo.Model.Project;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class ProjectRepository  implements ProjectRepositoryI{

    private final JdbcTemplate template;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public Optional<Project> getById(Long id) {
        List<Project> res = template.query(
                "SELECT * FROM Project WHERE id = ?",
                (rs, rowNum) -> new Project(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("begin_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                ),
                id
        );

        if (res.isEmpty())
            return Optional.empty();

        return Optional.of(res.getFirst());
    }


    @Override
    public Optional<Project> create(String name, String description, LocalDate begin, LocalDate end) {
        Long id = template.queryForObject("SELECT nextval('project_ID')", Long.class);

        template.update("INSERT INTO Project VALUES (?,?,?,?,?)",
                id,
                name,
                description,
                begin,
                end
        );

        return Optional.of(new Project(id, name, description, begin, end));
    }



    @Override
    public Set<Project> getAllProject() {
        return new HashSet<>(
                template.query(
                        "SELECT * FROM Project",
                        (rs, rowNum) -> new Project(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDate("begin_date").toLocalDate(),
                                rs.getDate("end_date").toLocalDate()
                        )
                )
        );
    }


    @Override
    public int delete(Long id) {
        return template.update("DELETE FROM Project WHERE id = ?", id);

    }

    @Override
    public boolean update(Project project){
        return template.update("UPDATE Project SET name = ?,description = ?,begin_date = ?,end_date = ? WHERE id = ?",
                project.getName()/*.substring(0,Math.min(name.length(),255))*/,
                project.getDescription(),
                project.getBegin(),
                project.getEnd(),
                project.getId()
        ) == 1;
    }

    @Override
    public Set<Project> getByRange(LocalDate begin, LocalDate end) {
        return new HashSet<>(
                template.query(
                        "SELECT * FROM Project WHERE begin_date >= ? AND \"end_date\" <= ?",
                        (rs, rowNum) -> new Project(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDate("begin_date").toLocalDate(),
                                rs.getDate("end_date").toLocalDate()
                        ),
                        begin,
                        end
                )
        );
    }

}

