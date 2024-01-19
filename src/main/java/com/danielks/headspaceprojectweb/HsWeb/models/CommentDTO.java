package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.Post;
import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CommentDTO(
        UUID id,
        @NotNull User user,
        @NotNull Post post,
        @NotBlank(message = "O corpo do comentário não pode estar em branco") String body,
        LocalDate create_time
) { }
