package com.example.demo.Repository;

import com.example.demo.Model.Project;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class ProjectRepository {

   /* private JdbcTemplate template;
    @Autowired
	public ProjectRepository(JdbcTemplate template) {
		this.template = template;
	}
	public Optional<Project> getById(Long id) {
		List<Project> res = template.query(
				"SELECT * FROM Project WHERE id = ?",
				new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Project(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getString("desc")
						);
					}
				},
				id
		);
		if (res.isEmpty())
			return Optional.empty();
		return Optional.of(res.getFirst());
	}*/
}
