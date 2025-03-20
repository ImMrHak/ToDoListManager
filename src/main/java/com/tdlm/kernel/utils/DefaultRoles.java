package com.tdlm.kernel.utils;

import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.tdlm.kernel.utils.DefaultRolePrivileges.*;

@RequiredArgsConstructor
public enum DefaultRoles {
    ROLE_ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_WRITE,
                    ADMIN_DELETE
            )
    ),
    ROLE_USER(
            Set.of(
                    USER_READ,
                    USER_WRITE,
                    USER_DELETE
            )
    ),
    ROLE_HELPER(
            Set.of(
                    HELPER_READ,
                    HELPER_WRITE,
                    HELPER_DELETE
            )
    );

    public final Set<DefaultRolePrivileges> defaultRolePrivileges;
}
