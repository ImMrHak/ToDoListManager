package com.tdlm.domain.user.projection;

import java.util.UUID;

public interface UserProjection {
    UUID getUserId();
    String getUsername();
    String getPassword();
    String getEmail();

}
