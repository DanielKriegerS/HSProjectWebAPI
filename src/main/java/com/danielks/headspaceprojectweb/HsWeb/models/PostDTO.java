package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PostDTO(
        UUID id,
        @NotNull UUID userId,
        @NotBlank(message = "O cabeçalho não pode estar em branco!") String header,
        @NotBlank(message = "A descrição não pode estar em branco!") String desc,
        @NotBlank(message = "O corpo não pode estar em branco!") String body,
        LocalDate create_time
){ }