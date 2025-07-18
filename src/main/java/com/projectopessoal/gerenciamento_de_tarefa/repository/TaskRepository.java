package com.projectopessoal.gerenciamento_de_tarefa.repository;

import com.projectopessoal.gerenciamento_de_tarefa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}