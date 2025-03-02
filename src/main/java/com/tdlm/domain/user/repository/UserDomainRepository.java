package com.tdlm.domain.user.repository;

import com.tdlm.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDomainRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
