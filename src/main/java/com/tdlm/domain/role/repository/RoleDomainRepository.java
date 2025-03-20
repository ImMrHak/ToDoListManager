package com.tdlm.domain.role.repository;

import com.tdlm.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDomainRepository extends JpaRepository<Role, Long> {
}
