package com.tdlm.kernel.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DefaultRolePrivileges {
    ADMIN_READ("ADMIN:READ"),
    ADMIN_WRITE("ADMIN:WRITE"),
    ADMIN_DELETE("ADMIN:DELETE"),
    ADMIN_UPDATE("ADMIN:UPDATE"),

    USER_READ("USER:READ"),
    USER_WRITE("USER:WRITE"),
    USER_DELETE("USER:DELETE"),

    HELPER_READ("HELPER:READ"),
    HELPER_WRITE("HELPER:WRITE"),
    HELPER_DELETE("HELPER:DELETE"),
    ;


    public final String authority;
}
