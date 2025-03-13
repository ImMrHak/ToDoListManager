package com.tdlm.domain.todo.repository;

import com.tdlm.domain.todo.model.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDomainRepository extends JpaRepository<ToDo, Long> {
    Page<ToDo> findAllByUser_Username(String userUsername, Pageable pageable);
}
