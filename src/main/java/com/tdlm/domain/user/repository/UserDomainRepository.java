package com.tdlm.domain.user.repository;

import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDomainRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByEmail(String email);


    // THIS IS JUST A TEST !!! : // EXAMPLE PROJECTION WORKING PERFECTLY CHECK TdlmApplication
    UserProjection findUserByUsername(String username);
}
