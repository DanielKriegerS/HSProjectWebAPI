package com.danielks.headspaceprojectweb.HsWeb.entities.roles;

public enum UserRoles {
    ADMIN("ADMIN"),
    PAYINGUSER("PAYINGUSER"),
    STANDARDUSER("STANDARDUSER"),
    USER("USER");

    private String user_role;

    UserRoles(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_role() {
        return user_role;
    }
}
