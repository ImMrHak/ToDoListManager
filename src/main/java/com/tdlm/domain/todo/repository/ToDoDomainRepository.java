package com.tdlm.domain.todo.repository;

import com.tdlm.domain.todo.model.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToDoDomainRepository extends JpaRepository<ToDo, UUID> {
    Page<ToDo> findAllByOwner_Username(String userUsername, Pageable pageable);
    Long countByTitleAndOwner_Username(String title, String username);
}
