package com.danielks.headspaceprojectweb.HsWeb.models;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ReactionDTO {
    private UUID id;
    @NotNull
    private User user;
    @NotNull
    private UUID entityId;
    @NotNull
    private String entity_type;
    @NotNull
    private String react_type;
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

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getReact_type() {
        return react_type;
    }

    public void setReact_type(String react_type) {
        this.react_type = react_type;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }
}
