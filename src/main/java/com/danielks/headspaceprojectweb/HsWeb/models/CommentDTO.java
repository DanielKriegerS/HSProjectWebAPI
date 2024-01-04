package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.Post;
import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class CommentDTO {
    private UUID id;
    @NotNull
    private User user;
    @NotNull
    private Post post;
    @NotBlank(message = "O corpo do comentário não pode estar em branco")
    private String body;
    private LocalDate create_time;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }
}
