package com.danielks.headspaceprojectweb.HsWeb.entities.roles;

public enum UserRoles {
    ADMIN("admin"),
    PAYINGUSER("payinguser"),
    STANDARDUSER("standarduser"),
    USER("user");

    private String user_role;

    UserRoles(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_role() {
        return user_role;
    }
}
