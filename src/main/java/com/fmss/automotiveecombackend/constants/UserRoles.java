package com.fmss.automotiveecombackend.constants;

public class UserRoles {
    public static final String IS_ADMIN = "hasRole('ROLE_client_admin')";
    public static final String IS_USER = "hasRole('ROLE_client_user')";
    public static final String IS_ADMIN_OR_USER = "hasAnyRole('ROLE_client_admin', 'ROLE_client_user')";
}
