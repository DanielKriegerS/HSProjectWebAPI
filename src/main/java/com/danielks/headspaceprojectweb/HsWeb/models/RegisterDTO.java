package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.roles.UserRoles;

public record RegisterDTO(String userLogin, String passHash, UserRoles role) {
}
