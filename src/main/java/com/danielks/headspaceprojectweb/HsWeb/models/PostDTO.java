package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class PostDTO {
    private UUID id;
    @NotNull
    private User user;
    @NotBlank(message = "O cabeçalho não pode estar em branco!")
    private String header;
    @NotBlank(message = "A descrição não pode estar em branco!")
    private String desc;
    @NotBlank(message = "O corpo não pode estar em branco!")
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
