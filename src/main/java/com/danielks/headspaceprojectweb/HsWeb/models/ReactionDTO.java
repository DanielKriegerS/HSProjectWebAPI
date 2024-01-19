package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ReactionDTO(
        UUID id,
        @NotNull User user,
        @NotNull UUID entityId,
        @NotNull String entity_type,
        @NotNull String react_type,
        LocalDate create_time
){
}
