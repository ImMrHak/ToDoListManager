package com.tdlm.domain.task.repository;

import com.tdlm.domain.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDomainRepository extends JpaRepository<Task, Long> {
}
