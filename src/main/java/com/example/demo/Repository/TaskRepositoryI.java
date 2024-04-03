package com.example.demo.Repository;

import com.example.demo.Entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryI extends JpaRepository<Task,Integer> {
    Task findByIdAndProjectId(int tId, int pId);
    List<Task>  findAllByProjectId(int id);
    @Transactional
    void deleteAllByProjectId(int id);
    @Transactional
    void deleteByIdAndProjectId(int tId, int pId);
    @Transactional
    void deleteByProjectIdAndIsfinishedTrue(int pId);
}
