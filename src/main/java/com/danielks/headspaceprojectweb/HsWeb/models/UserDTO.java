package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.roles.UserRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
        UUID id,
        @NotBlank(message = "O nome não pode estar em branco!") String userName,
        @NotBlank(message = "O login não pode estar em branco!") String userLogin,
        @NotBlank(message = "A senha não pode estar em branco!") String passHash,
        @NotBlank(message = "O telefone não pode estar em branco!") Long phone,
        @NotBlank(message = "O endereço não pode estar em branco!") String address,
        @NotBlank(message = "O email não pode estar em branco!") String email,
        @NotBlank(message = "A idade não pode estar em branco!") int age,
        @NotNull UserRoles user_role,
        LocalDateTime create_time
){ }
