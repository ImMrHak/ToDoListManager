package com.tdlm.domain.permission.repository;

import com.tdlm.domain.permission.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeDomainRepository extends JpaRepository<Privilege, Long> {
}
